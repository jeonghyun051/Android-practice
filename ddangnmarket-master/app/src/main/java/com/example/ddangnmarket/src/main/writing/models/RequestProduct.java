package com.example.ddangnmarket.src.main.writing.models;

import com.google.gson.annotations.SerializedName;

public class RequestProduct {
    @SerializedName("title")
    private String title;

    @SerializedName("categoriesNo")
    private int categoriesNo;

    @SerializedName("price")
    private int price;

    @SerializedName("text")
    private String text;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoriesNo(int categoriesNo) {
        this.categoriesNo = categoriesNo;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setText(String text) {
        this.text = text;
    }
}
