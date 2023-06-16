package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditPlans extends AppCompatActivity implements AdapterSingleDayPlan.RemovePlanInterface{
    private TextView planDay;
    private RecyclerView recyclerView;
    private Button AddPlan;
    private AdapterSingleDayPlan adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_plans_layout);
        initView();
         adapter=new AdapterSingleDayPlan(this);
        Intent intent=getIntent();
        if(null!=intent){
            String day=intent.getStringExtra("Day");
            if(null!=day){
                ArrayList<Plan> plans=Utils.getInstance(this).GetPlansByDay(day);
                planDay.setText(day);
                adapter.setPlans(plans);

            }
        }
        adapter.setType("edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditPlans.this,AllActivities.class);
                startActivity(intent);
            }
        });

    }
    protected void initView(){
        planDay=findViewById(R.id.editPlanDay);
        recyclerView=findViewById(R.id.editRecylerView);
        AddPlan=findViewById(R.id.editAddPlanBtn);
    }

    @Override
    public void RemovePlanResult(Plan plan) {
        ArrayList<Plan> plans= Utils.getInstance(this).getAllPlans();
        adapter.setPlans(plans);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
