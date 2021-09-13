package com.example.ddangnmarket.src.login.interfaces;

import com.example.ddangnmarket.src.login.models.LoginResponse;

public interface LoginActivityView {
    void validateMessageSuccess(boolean isSuccess, int code, String message);

    void validateMessageFailure();

    void validatePhoneCertSuccess(boolean isSuccess, int code, String message);

    void validatePhoneCertFailure();

    void validateJwtSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result);

    void validateJwtFailure();
}
