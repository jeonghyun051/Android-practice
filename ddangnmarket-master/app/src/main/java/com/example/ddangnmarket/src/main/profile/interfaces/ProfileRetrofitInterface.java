package com.example.ddangnmarket.src.main.profile.interfaces;

import com.example.ddangnmarket.src.main.profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileRetrofitInterface {

    @GET("/user/{userNo}")
    Call<ProfileResponse> getProfilePath(
            @Path("userNo") final int userNo
    );
}
