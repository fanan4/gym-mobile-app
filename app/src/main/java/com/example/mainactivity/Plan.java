package com.example.mainactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {
    private Movement movement;
    private Integer Minutes;
    private String days;
    boolean isAccomplished=false;
    private int id;
    public Plan(int id,Movement movement, Integer minutes, String days, boolean isAccomplished) {
        this.movement = movement;
        Minutes = minutes;
        this.days = days;
        this.isAccomplished = isAccomplished;
        this.id=id;
    }

    protected Plan(Parcel in) {
        id=in.readInt();
        movement = in.readParcelable(Movement.class.getClassLoader());
        Minutes = in.readInt();
        days = in.readString();
        isAccomplished = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };
    public int  getId(){ return id ; }

    public Movement getMovement() {
        return movement;
    }

    public int getMinutes() {
        return Minutes;
    }

    public String getDays() {
        return days;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setId(int id){ this.id=id; }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void setMinutes(int minutes) {
        Minutes = minutes;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(movement, i);
        parcel.writeInt(Minutes);
        parcel.writeString(days);
        parcel.writeByte((byte) (isAccomplished ? 1 : 0));
        parcel.writeInt(id);
    }
}
