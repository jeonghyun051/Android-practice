package com.example.ddangnmarket.src.Profile.models;

import com.google.gson.annotations.SerializedName;

public class RequestProfile {

    @SerializedName("profileUrl")
    private String profileUrl;

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
