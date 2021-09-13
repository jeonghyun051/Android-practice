package com.example.ddangnmarket.src.main.profile.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileResponse {

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

    public class Result implements Serializable {
        @SerializedName("profileUrl")
        private String profileUrl;
        @SerializedName("id")
        private String id;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("address")
        private String address;
        @SerializedName("cert")
        private int cert;

        public String getProfileUrl() {
            return profileUrl;
        }

        public String getId() {
            return id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getAddress() {
            return address;
        }

        public int getCert() {
            return cert;
        }
    }
}
