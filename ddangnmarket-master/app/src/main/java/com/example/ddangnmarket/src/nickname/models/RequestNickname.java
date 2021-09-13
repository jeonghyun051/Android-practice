package com.example.ddangnmarket.src.nickname.models;

import com.google.gson.annotations.SerializedName;

public class RequestNickname {
    @SerializedName("id")
    private String id;

    @SerializedName("phoneNum")
    private String phoneNum;

    @SerializedName("locationNo")
    private int locationNo;

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setLocation(int locationNo) {
        this.locationNo = locationNo;
    }
}
