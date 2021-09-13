package com.example.ddangnmarket.src.main.chat.interfaces;

import com.example.ddangnmarket.src.main.chat.models.ChatResponse;

public interface ChatActivityView {

    void validatePurchaseSuccess(boolean isSuccess, int code, String message, ChatResponse.Result result);

    void validatePurchaseFailure(String message);
}
