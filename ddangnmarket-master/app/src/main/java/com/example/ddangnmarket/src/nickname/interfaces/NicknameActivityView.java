package com.example.ddangnmarket.src.nickname.interfaces;

import com.example.ddangnmarket.src.login.models.LoginResponse;

public interface NicknameActivityView {

    void validateNicknameSuccess(boolean isSuccess, int code, String message);

    void validateNicknameFailure();

    void validateJwtSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result);

    void validateJwtFailure();
}
