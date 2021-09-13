package com.example.ddangnmarket.src.LocationCertification.interfaces;

import com.example.ddangnmarket.src.LocationCertification.models.LocationResponse;
import com.example.ddangnmarket.src.LocationCertification.models.RequestLocation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationRetrofitInterface {

    @POST("/user/location")
    Call<LocationResponse> postLocation(@Body RequestLocation params);
}
