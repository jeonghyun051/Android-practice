package com.example.ddangnmarket.src.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestJwt {
    @SerializedName("phoneNum")
    private String phoneNum;

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
