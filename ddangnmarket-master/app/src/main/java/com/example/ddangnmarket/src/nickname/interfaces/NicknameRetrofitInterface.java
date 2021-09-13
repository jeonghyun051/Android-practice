package com.example.ddangnmarket.src.nickname.interfaces;

import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.nickname.models.RequestNickname;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NicknameRetrofitInterface {

    @POST("/user")
    Call<LoginResponse> postNickname(@Body RequestNickname params);

}
