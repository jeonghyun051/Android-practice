package com.example.ddangnmarket.src.main.home.models;

import com.google.gson.annotations.SerializedName;

public class ResultProduct {

    @SerializedName("productNo")
    int productNo;

    @SerializedName("imageUrl")
    String imageUrl;

    @SerializedName("reroll")
    int reroll;

    @SerializedName("title")
    String title;

    @SerializedName("address")
    String address;

    @SerializedName("price")
    int price;

    @SerializedName("chat")
    int chat;

    @SerializedName("comments")
    int comments;

    @SerializedName("favorite")
    int favorite;

    public int getProductNo() {
        return productNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getReroll() {
        return reroll;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public int getChat() {
        return chat;
    }

    public int getComments() {
        return comments;
    }

    public int getFavorite() {
        return favorite;
    }
}
