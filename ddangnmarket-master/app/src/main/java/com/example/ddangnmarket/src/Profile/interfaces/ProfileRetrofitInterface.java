package com.example.ddangnmarket.src.Profile.interfaces;

import com.example.ddangnmarket.src.Profile.models.ProfileResponse;
import com.example.ddangnmarket.src.Profile.models.RequestProfile;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ProfileRetrofitInterface {

    @PATCH("/user")
    Call<ProfileResponse> patchProfile(@Body RequestProfile params);
}
