package com.example.ddangnmarket.src.main.writing.models;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private Result result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public Result getResult() {
        return result;
    }
}
