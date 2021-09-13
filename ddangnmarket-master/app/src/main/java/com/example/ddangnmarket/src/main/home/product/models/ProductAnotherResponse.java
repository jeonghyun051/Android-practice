package com.example.ddangnmarket.src.main.home.product.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductAnotherResponse {
    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public ArrayList<Result> getResult() {
        return result;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public class Result implements Serializable {
        @SerializedName("productNo")
        private int productNo;
        @SerializedName("title")
        private String title;
        @SerializedName("price")
        private int price;
        @SerializedName("imageUrl")
        private String imageUrl;

        public int getProductNo() {
            return productNo;
        }

        public String getTitle() {
            return title;
        }

        public int getPrice() {
            return price;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
