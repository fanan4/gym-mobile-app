package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovemetDetails extends AppCompatActivity implements PlanDialogDetail.GetPlanInterface{
    private TextView MovementName,MovementLongDesc;
    private ImageView MovementImg;
    private Button addPlanBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
         setContentView(R.layout.mov_details_layout);
         initView();
         Intent intent =getIntent();
         if(null !=intent){
             Movement movement=intent.getParcelableExtra("Movement");
               if( null !=movement){
                    MovementName.setText(movement.getTitle());
                    MovementLongDesc.setText(movement.getLongDesc());
                   Glide.with(this)
                         .asBitmap()
                         .load(movement.getImgUrl())
                         .into(MovementImg);
                   addPlanBtn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           PlanDialogDetail planDialogDetail=new PlanDialogDetail();
                           Bundle bundle=new Bundle();
                           bundle.putParcelable("Train",movement);
                           planDialogDetail.setArguments(bundle);
                           planDialogDetail.show(getSupportFragmentManager(),"show the dialog");
                       }
                   });

               }
         }

    }
    public void initView(){
        MovementName=findViewById(R.id.TraningName);
        MovementLongDesc=findViewById(R.id.LongDesc);
        MovementImg=findViewById(R.id.TrainingImg);
        addPlanBtn=findViewById(R.id.AddPlanBtn);
    }

    @Override
    public void getPlan(Plan plan) {
           Utils.getInstance(this).addPlan(plan);
           Intent intent=new Intent(this,PlanActivitie.class);
           intent.putExtra("plan",plan);
           startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
