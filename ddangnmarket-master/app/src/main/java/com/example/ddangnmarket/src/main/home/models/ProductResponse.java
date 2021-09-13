package com.example.ddangnmarket.src.main.home.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private ArrayList<ResultProduct> resultProduct;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public ArrayList<ResultProduct> getResultProduct() {
        return resultProduct;
    }
}
