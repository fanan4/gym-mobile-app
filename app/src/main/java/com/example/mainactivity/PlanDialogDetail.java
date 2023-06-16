package com.example.mainactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.DialogFragment;

public class PlanDialogDetail extends DialogFragment {

    public interface GetPlanInterface {
        void  getPlan(Plan plan);
    }
    private GetPlanInterface getPlanInterface;
    private TextView TrainingName;
    private EditText Minutes;
    private Button Dismiss,Add;
    private Spinner spinner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=getActivity().getLayoutInflater().inflate(R.layout.dalog_plan_detail,null);
        builder.setView(view);
        initView(view);
        Bundle bundle=getArguments();
        if(null !=bundle){
            Movement mov=bundle.getParcelable("Train");
            if(null !=mov){
                TrainingName.setText(mov.getTitle());
                Dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         String day=spinner.getSelectedItem().toString();
                         //Integer Minutess=Integer.valueOf(Minutes.getText().toString());
                          int id=Utils.getInstance(getActivity()).generateRandomId();
                           Plan plan=new Plan(id,mov,10,day,false);
                        try {
                            getPlanInterface=(GetPlanInterface) getActivity();
                            getPlanInterface.getPlan(plan);
                            dismiss();
                        }catch (ClassCastException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        return  builder.create();
    }
    public void initView(View view){
        TrainingName=view.findViewById(R.id.TrainingName);
        Minutes=view.findViewById(R.id.minutes);
        Dismiss=view.findViewById(R.id.Dismiss);
        Add=view.findViewById(R.id.Add);
        spinner=view.findViewById(R.id.spinner);
    }
}
