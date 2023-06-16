package com.example.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class AdapterPlanAllDays extends RecyclerView.Adapter<AdapterPlanAllDays.viewHolder> {
    Context context;

    public AdapterPlanAllDays(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_plan_rec,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String day=Utils.getInstance(context).generateDays();
        System.out.println("the day is "+day);
        holder.PlanDay.setText(day);
        holder.EditPlan.setText("Edit");
        holder.EditPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent =new Intent(context,EditPlans.class);
                 intent.putExtra("Day",day);
                 context.startActivity(intent);
            }
        });
        AdapterSingleDayPlan adapter=new AdapterSingleDayPlan(context);
        ArrayList<Plan> plans=Utils.getInstance(context).GetPlansByDay(day);
        adapter.setPlans(plans);
        holder.TrainRecView.setAdapter(adapter);
        holder.TrainRecView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView PlanDay,EditPlan;
        private RecyclerView TrainRecView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            PlanDay=itemView.findViewById(R.id.planDay);
            EditPlan=itemView.findViewById(R.id.editText);
            TrainRecView=itemView.findViewById(R.id.DayPlanRec);

        }
    }
}
