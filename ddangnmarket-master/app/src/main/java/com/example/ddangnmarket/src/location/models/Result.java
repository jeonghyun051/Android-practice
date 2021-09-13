package com.example.ddangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("locationNo")
    int locationNo;

    @SerializedName("address")
    String address;

    public int getLocationNo() {
        return locationNo;
    }

    public String getAddress() {
        return address;
    }
}
