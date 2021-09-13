package com.example.ddangnmarket.src.main.chat;

import android.content.Intent;
import android.os.Bundle;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.chat.interfaces.ChatActivityView;
import com.example.ddangnmarket.src.main.chat.models.ChatResponse;
import com.example.ddangnmarket.src.main.chat.models.RequestPurchase;

public class ChatActivity extends BaseActivity implements ChatActivityView {
    int productNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        productNo = intent.getIntExtra("productNo",1);
        purchase(productNo);
        System.out.println("productNo : " + productNo);
    }

    public void purchase(int productNo){
        ChatService chatService = new ChatService(this);
        RequestPurchase requestPurchase = new RequestPurchase();
        requestPurchase.setProductNo(productNo);
        chatService.postPurchase(requestPurchase);
    }

    @Override
    public void validatePurchaseSuccess(boolean isSuccess, int code, String message, ChatResponse.Result result) {
        if(isSuccess){
            showCustomToast(message);
            System.out.println(result.getPurchaseNo().get(0).getPurchaseNo());
        }else{
            showCustomToast(message);
        }
    }

    @Override
    public void validatePurchaseFailure(String message) {
        showCustomToast(message);
    }
}
