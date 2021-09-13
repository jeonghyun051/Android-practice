package com.example.ddangnmarket.src.main.home.product.models;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("userNo")
    int userNo;

    @SerializedName("productNo")
    int productNo;

    @SerializedName("id")
    String id;

    @SerializedName("address")
    String address;

    @SerializedName("manner")
    Float manner;

    @SerializedName("title")
    String title;

    @SerializedName("categories")
    String categories;

    @SerializedName("reroll")
    int reroll;

    @SerializedName("text")
    String text;

    @SerializedName("chat")
    int chat;

    @SerializedName("favorite")
    int favorite;

    @SerializedName("hits")
    int hits;

    @SerializedName("price")
    int price;

    public int getUserNo() {
        return userNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Float getManner() {
        return manner;
    }

    public String getTitle() {
        return title;
    }

    public String getCategories() {
        return categories;
    }

    public int getReroll() {
        return reroll;
    }

    public String getText() {
        return text;
    }

    public int getChat() {
        return chat;
    }

    public int getFavorite() {
        return favorite;
    }

    public int getHits() {
        return hits;
    }

    public int getPrice() {
        return price;
    }
}