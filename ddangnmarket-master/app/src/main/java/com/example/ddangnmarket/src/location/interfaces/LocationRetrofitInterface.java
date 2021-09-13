package com.example.ddangnmarket.src.location.interfaces;

import com.example.ddangnmarket.src.location.models.LocationResponse;
import com.example.ddangnmarket.src.location.models.RequestLocation;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationRetrofitInterface {
    @GET("/location")
    Call<LocationResponse> getLocationQuery(
            @Query("address") final String address
    );

    @POST("/location")
    Call<LocationResponse> postLocation(@Body RequestLocation params);
}
