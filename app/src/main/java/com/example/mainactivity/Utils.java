package com.example.mainactivity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Utils {
    private static  Utils instance;
    private static ArrayList<String> Days=new ArrayList<>();
    private  static  SharedPreferences sharedPreferences;
    private static  final String AllMov="allMovement",Data_base="MyDataBase",All_Plan="AddMov";
    private static int i=0;
    Random random=new Random();
    private Utils(Context context) {
        sharedPreferences=context.getSharedPreferences(Data_base,Context.MODE_PRIVATE);
        initDays();
        if(null==getAllMovement()){
            initData();
        }
        if(null==getAllPlans()){
            ArrayList<Plan> plans=new ArrayList<>();
            Gson gson=new Gson();
            SharedPreferences.Editor editor =sharedPreferences.edit();
            
            editor.putString(All_Plan,gson.toJson(plans));
            editor.commit();
        }
    }
    public static Utils getInstance(Context context){
        if(instance!=null){
            return instance;
        }
        else{
            instance= new Utils(context);
            return  instance;
        }
    }
    private  void initData(){
        System.out.println("hello in init data");
        ArrayList<Movement> movements=new ArrayList<>();
        movements.add(new Movement(1,"PushMov","https://acewebcontent.azureedge.net/expert-articles/2016/02/2016-02-29-movement-training.jpg",
                ", take one exercise and make a column for how to adjust the movement in that plane",
                "The idea of training multiplanar movement patterns has long been advocated by Gary Gray," +
                        " "));
        movements.add(new Movement(2,"PushMov","https://www.muscleandfitness.com/wp-content/uploads/2019/03/1109-squat2.jpg?w=800&quality=86&strip=all",
                ", lqshdfDFHKJAZHFKH",
                "The idea of training multiplanar movement patterns has long been advocated by Gary Gray,"
                        ));
        Gson gson=new Gson();
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(AllMov,gson.toJson(movements));
        editor.commit();


    }
    public static  void clearPlans(){
        ArrayList<Plan> plans=new ArrayList<>();
        Gson gson=new Gson();
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.remove(All_Plan);
        editor.putString(All_Plan,gson.toJson(plans));
        editor.commit();

    }
    public ArrayList<Movement> getAllMovement(){
        ArrayList<Movement> movements;
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Movement>>(){}.getType();
        movements=gson.fromJson(sharedPreferences.getString(AllMov,null),type);
        return movements;
    }
    public void addPlan(Plan plan){
        ArrayList<Plan> plans=getAllPlans();
        plans.add(plan);
        Gson gson=new Gson();
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.remove(All_Plan);
        editor.putString(All_Plan,gson.toJson(plans));
        editor.commit();
    }
    public static ArrayList<Plan> getAllPlans(){
        ArrayList<Plan> plans;
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Plan>>(){}.getType();
        plans=gson.fromJson(sharedPreferences.getString(All_Plan,null),type);
        return plans;
    }
    public void initDays(){
        Days.add("Monday");
        Days.add("Tuesday");
        Days.add("Wednesday");
        Days.add("Thursday");
        Days.add("Friday");
        Days.add("Saturday");
        Days.add("Sunday");
    }
    public static String generateDays(){
        if(i>=7){
            i=0;
        }
         if(i>=0 && i<=6){
             String day=Days.get(i);
             i++;
             return day;
         }
         return "";
    }
    public static  ArrayList<Plan> GetPlansByDay(String day){
        ArrayList<Plan> plans=new ArrayList<>();
        ArrayList<Plan> allPlans=getAllPlans();
        for(Plan b:allPlans){
            System.out.println("plan day is "+b.getDays());
            if(b.getDays().equals(day)){
                System.out.println("get plan by day"+b.getDays());
                plans.add(b);
            }
        }
        return plans;
    }

    public static ArrayList<Plan> UpdatePlans(Plan plan ){
        ArrayList<Plan> plans=getAllPlans();
        ArrayList<Plan> newPlan=new ArrayList<>();
        for(Plan p:plans){
            if(p.getId()==plan.getId()){
                System.out.println("plan equalssssssss");
               p.setAccomplished(true);
               newPlan.add(p);
            }
            else{
                newPlan.add(p);
            }
        }
        Gson gson =new Gson();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(All_Plan);
        editor.putString(All_Plan, gson.toJson(newPlan));
        editor.commit();
        //System.out.println("the updated plans are "+plans);
        return plans;
    }
    public static ArrayList<Plan> RemovePlan(Plan plan){
        ArrayList<Plan> plans=getAllPlans();
        if(null !=plan){
            for(Plan p:plans){
                if(p.getId()==p.getId()){
                    plans.remove(p);
                    Gson gson =new Gson();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove(All_Plan);
                    editor.putString(All_Plan, gson.toJson(plans));
                    editor.commit();
                    return plans;
                }
            }
        }
        return plans;
    }
    public int generateRandomId(){
        int id=random.nextInt(1000);
        return id;
    }

}
