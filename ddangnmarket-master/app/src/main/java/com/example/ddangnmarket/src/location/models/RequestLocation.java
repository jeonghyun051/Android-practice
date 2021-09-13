package com.example.ddangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

public class RequestLocation {

    @SerializedName("locationNo")
    private int locationNo;

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }

}
