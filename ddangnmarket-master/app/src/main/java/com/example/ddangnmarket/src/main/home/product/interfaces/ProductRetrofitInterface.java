package com.example.ddangnmarket.src.main.home.product.interfaces;

import com.example.ddangnmarket.src.main.home.product.models.ProductAnotherResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {
    @GET("/product/{productNo}")
    Call<ProductResponse> getProductPath(
            @Path("productNo") final int productNo
    );

    @GET("/product/{productNo}/image")
    Call<ProductImageResponse> getProductImagePath(
            @Path("productNo") int productNo
    );

    @GET("/user/{userNo}/product")
    Call<ProductAnotherResponse> getProductAnotherPath(
            @Path("userNo") final int userNo
    );
}
