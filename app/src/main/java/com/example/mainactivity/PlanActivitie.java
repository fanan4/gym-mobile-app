package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanActivitie extends AppCompatActivity {
    RelativeLayout withPlan,NoPlan;
    RecyclerView recyclerView;
    Button AddPlanBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paln_activitie_layout);
        //method to init all the views
         initView();
         NoPlan=findViewById(R.id.withNoPlan);
         ArrayList<Plan> plans=Utils.getInstance(this).getAllPlans();
         //check if there is some plans
         if(plans.size()>0){
             withPlan.setVisibility(View.VISIBLE);
             NoPlan.setVisibility(View.GONE);
         }
         else{
             withPlan.setVisibility(View.GONE);
             NoPlan.setVisibility(View.VISIBLE);
         }
         AddPlanBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(PlanActivitie.this,AllActivities.class);
                 startActivity(intent);
             }
         });
         //set the adapter for the recycler view
         AdapterPlanAllDays adapter=new AdapterPlanAllDays(this);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
         
    }
    protected void initView(){
        recyclerView=findViewById(R.id.PlanRecyclerView);
        withPlan=findViewById(R.id.withPlan);
        NoPlan=findViewById(R.id.withNoPlan);
        AddPlanBtn=findViewById(R.id.NoPlanaddPlan);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
