package com.example.ddangnmarket.src.main.profile.interfaces;

import com.example.ddangnmarket.src.main.profile.models.ProfileResponse;

import java.util.ArrayList;

public interface ProfileActivityView {

    void validateProfileSuccess(boolean isSuccess, int code, String message, ArrayList<ProfileResponse.Result> results);

    void validateProfileFailure(String message);
}
