package com.example.mainactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class Movement implements Parcelable {
    private  int id;
    private String Title;
    private String ImgUrl;
    private String ShortDesc;
    private String LongDesc;

    public Movement(int id, String title, String imgUrl, String shortDesc,String longDesc) {
        this.id = id;
        this.Title = title;
        this.ImgUrl = imgUrl;
        this.ShortDesc = shortDesc;
        this.LongDesc = longDesc;
    }

    protected Movement(Parcel in) {
        id = in.readInt();
        Title = in.readString();
        ImgUrl = in.readString();
        ShortDesc = in.readString();
        LongDesc = in.readString();
    }

    public static final Creator<Movement> CREATOR = new Creator<Movement>() {
        @Override
        public Movement createFromParcel(Parcel in) {
            return new Movement(in);
        }

        @Override
        public Movement[] newArray(int size) {
            return new Movement[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public String getLongDesc() {
        return LongDesc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        LongDesc = longDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(Title);
        parcel.writeString(ImgUrl);
        parcel.writeString(ShortDesc);
        parcel.writeString(LongDesc);
    }
}
