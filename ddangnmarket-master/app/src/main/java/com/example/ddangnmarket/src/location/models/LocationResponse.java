package com.example.ddangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private ArrayList<Result> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public ArrayList<Result> getResult() {
        return result;
    }
}
