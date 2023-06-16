package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllActivities extends AppCompatActivity {
    private RecyclerView recyclerViewAll;
    private ActivityRecView adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_layout);
        recyclerViewAll=findViewById(R.id.recyclViewAll);
        adapter=new ActivityRecView(this);
        ArrayList<Movement> movements=Utils.getInstance(this).getAllMovement();
        if(null!=movements){
            adapter.setMovements(movements);
            recyclerViewAll.setAdapter(adapter);
            recyclerViewAll.setLayoutManager( new GridLayoutManager(this,2));
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
