package com.example.ddangnmarket.src.LocationCertification.models;

import com.google.gson.annotations.SerializedName;

public class RequestLocation {

    @SerializedName("x_axis")
    private Double x_axis;

    @SerializedName("y_axis")
    private Double y_axis;

    public void setX_axis(Double x_axis) {
        this.x_axis = x_axis;
    }

    public void setY_axis(Double y_axis) {
        this.y_axis = y_axis;
    }
}
