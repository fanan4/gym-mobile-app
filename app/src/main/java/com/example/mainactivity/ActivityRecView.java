package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActivityRecView extends  RecyclerView.Adapter<ActivityRecView.ViewHolder>{
    ArrayList<Movement> Movements=new ArrayList<>();
    private Context context;

    public ActivityRecView(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.MovTitle.setText(Movements.get(position).getTitle());
        Glide.with(context)
                .asBitmap()
                .load(Movements.get(position).getImgUrl())
                .into(holder.MovImg);
        holder.MovShortDesc.setText(Movements.get(position).getShortDesc());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MovemetDetails.class);
                intent.putExtra("Movement",  Movements.get(position));
                context.startActivity(intent);
            }
        });
    }

    public void setMovements(ArrayList<Movement> movements){
        this.Movements=movements;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return Movements.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView MovTitle,MovShortDesc;
        private ImageView MovImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            MovTitle=itemView.findViewById(R.id.MovTitle);
            MovShortDesc=itemView.findViewById(R.id.MovSortDesc);
            MovImg=itemView.findViewById(R.id.MovImg);
        }
    }
}
