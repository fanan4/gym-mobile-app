package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;

import java.util.ArrayList;

public class AdapterSingleDayPlan extends RecyclerView.Adapter<AdapterSingleDayPlan.viewHolder> {
    private ArrayList<Plan> plans=new ArrayList<>();
    private Context context;
    private String type="";
    public interface RemovePlanInterface{
        void RemovePlanResult(Plan plan);
    }
    private RemovePlanInterface removePlanInterface;
    public AdapterSingleDayPlan(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single_day,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
          holder.MovTitle.setText(plans.get(position).getMovement().getTitle());
          Glide.with(context)
                  .asBitmap()
                  .load(plans.get(position).getMovement().getImgUrl())
                  .into(holder.MovImg);
          holder.MovShortDesc.setText(plans.get(position).getMovement().getShortDesc());
          if(plans.get(position).isAccomplished()){
              holder.no_Checked_Circle.setVisibility(View.GONE);
              holder.checkCirlce.setVisibility(View.VISIBLE);
          }
          else{
              holder.no_Checked_Circle.setVisibility(View.VISIBLE);
              holder.checkCirlce.setVisibility(View.GONE);
          }
          holder.parent.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(context,MovemetDetails.class);
                  intent.putExtra("Movement",plans.get(position).getMovement());
                  context.startActivity(intent);
              }
          });

          if(type=="edit"){
              AlertDialog.Builder builder=new AlertDialog.Builder(context);
              builder.setTitle("finished");
              builder.setMessage("have you fininshed "+plans.get(position).getMovement().getTitle());
              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                  }
              });
              builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Utils.getInstance(context).UpdatePlans(plans.get(position));
                      System.out.println("after notify data");
                      removePlanInterface=(RemovePlanInterface) context;
                      removePlanInterface.RemovePlanResult(plans.get(position));
                      notifyDataSetChanged();

                  }
              });
              holder.no_Checked_Circle.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      builder.create().show();

                  }
              });
              holder.DeletePlanBtn.setVisibility(View.VISIBLE);
              holder.DeletePlanBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      AlertDialog.Builder builder=new AlertDialog.Builder(context);
                      builder.setTitle("Delete Plan");
                      builder.setMessage("are sur to delete "+plans.get(position).getMovement().getTitle());
                      builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {

                          }
                      });
                      builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              try{
                                  Utils.getInstance(context).RemovePlan(plans.get(position));
                                  removePlanInterface=(RemovePlanInterface) context;
                                  removePlanInterface.RemovePlanResult(plans.get(position));
                                  notifyDataSetChanged();

                              }catch (ClassCastException e){
                                  e.printStackTrace();
                              }
                          }
                      });
                      builder.create().show();
                  }
              });
          }

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }
    public void setType(String type){
        this.type=type;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private TextView MovTitle,MovShortDesc;
        private ImageView MovImg,checkCirlce,no_Checked_Circle;
        private Button DeletePlanBtn;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.Singlparent);
            MovTitle=itemView.findViewById(R.id.SinglMovTitle);
            MovShortDesc=itemView.findViewById(R.id.SingMovSortDesc);
            MovImg=itemView.findViewById(R.id.SingMovImg);
            checkCirlce=itemView.findViewById(R.id.checkedCirle);
            no_Checked_Circle=itemView.findViewById(R.id.noCheckedCircle);
            DeletePlanBtn=itemView.findViewById(R.id.DeletePlanBtn);
        }
    }
}
