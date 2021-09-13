package com.example.ddangnmarket.src.main.writing.interfaces;

import com.example.ddangnmarket.src.main.writing.models.ProductResponse;
import com.example.ddangnmarket.src.main.writing.models.RequestProduct;
import com.example.ddangnmarket.src.main.writing.models.RequestProductImage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WritingRetrofitInterface {
    @POST("/product")
    Call<ProductResponse> postProduct(@Body RequestProduct params);

    @POST("/product/image")
    Call<ProductResponse> postProductImage(@Body RequestProductImage params);
}
