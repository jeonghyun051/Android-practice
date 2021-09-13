package com.example.ddangnmarket.src.Profile.interfaces;

import com.example.ddangnmarket.src.login.models.LoginResponse;

public interface ProfileActivityView {

    void validateProfileSuccess(boolean isSuccess, int code, String message);

    void validateProfileFailure();
}
