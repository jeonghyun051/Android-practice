package com.example.ddangnmarket.src.main.chat;

import com.example.ddangnmarket.src.main.chat.interfaces.ChatActivityView;
import com.example.ddangnmarket.src.main.chat.interfaces.ChatRetrofitInterface;
import com.example.ddangnmarket.src.main.chat.models.ChatResponse;
import com.example.ddangnmarket.src.main.chat.models.RequestPurchase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ChatService {
    private final ChatActivityView chatActivityView;

    public ChatService(ChatActivityView chatActivityView) {
        this.chatActivityView = chatActivityView;
    }

    void postPurchase(RequestPurchase requestPurchase) {
        final ChatRetrofitInterface chatRetrofitInterface = getRetrofit().create(ChatRetrofitInterface.class);
        chatRetrofitInterface.postPurchase(requestPurchase).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                final ChatResponse chatResponse = response.body();

                chatActivityView.validatePurchaseSuccess(chatResponse.getIsSuccess(), chatResponse.getCode(), chatResponse.getMessage(), chatResponse.getResult());
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                chatActivityView.validatePurchaseFailure(t.getMessage());
            }
        });
    }
}
