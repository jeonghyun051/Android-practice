package com.example.ddangnmarket.src.main.chat.interfaces;

import com.example.ddangnmarket.src.main.chat.models.ChatResponse;
import com.example.ddangnmarket.src.main.chat.models.RequestPurchase;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatRetrofitInterface {
    @POST("/purchase")
    Call<ChatResponse> postPurchase(@Body RequestPurchase params);
}
