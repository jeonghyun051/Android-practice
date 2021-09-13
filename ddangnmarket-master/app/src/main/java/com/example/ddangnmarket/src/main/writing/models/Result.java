package com.example.ddangnmarket.src.main.writing.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("productNo")
    ArrayList<ProductNo> productNo;

    public ArrayList<ProductNo> getProductNo() {
        return productNo;
    }
}
