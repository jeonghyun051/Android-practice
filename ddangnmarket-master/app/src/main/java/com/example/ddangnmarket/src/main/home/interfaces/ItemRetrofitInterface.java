package com.example.ddangnmarket.src.main.home.interfaces;

import com.example.ddangnmarket.src.main.home.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemRetrofitInterface {

    @GET("/product")
    Call<ProductResponse> getProductQuery(
            @Query("address") final String address,
            @Query("page") final int page
    );
}
