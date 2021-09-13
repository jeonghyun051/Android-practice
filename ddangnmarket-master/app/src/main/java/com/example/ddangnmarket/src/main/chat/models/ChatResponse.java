package com.example.ddangnmarket.src.main.chat.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatResponse {

    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public Result getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public class Result {

        @SerializedName("purchaseNo")
        private ArrayList<PurchaseNo> purchaseNo;

        public ArrayList<PurchaseNo> getPurchaseNo() {
            return purchaseNo;
        }

        public class PurchaseNo {
            @SerializedName("purchaseNo")
            private int purchaseNo;

            public int getPurchaseNo() {
                return purchaseNo;
            }
        }
    }
}
