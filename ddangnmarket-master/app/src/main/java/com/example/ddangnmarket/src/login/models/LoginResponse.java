package com.example.ddangnmarket.src.login.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {
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

    public class Result {
        @SerializedName("jwt")
        String jwt;

        public String getJwt() {
            return jwt;
        }

        @SerializedName("userNo")
        ArrayList<UserNo> userNo;

        public ArrayList<UserNo> getUserNo() {
            return userNo;
        }

        public class UserNo{
            @SerializedName("userNo")
            int userNo;

            public int getUserNo() {
                return userNo;
            }
        }
    }

}
