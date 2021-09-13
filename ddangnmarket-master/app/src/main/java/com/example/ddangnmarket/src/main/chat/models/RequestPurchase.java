package com.example.ddangnmarket.src.main.chat.models;

import com.google.gson.annotations.SerializedName;

public class RequestPurchase {

    @SerializedName("productNo")
    private int productNo;

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }
}
