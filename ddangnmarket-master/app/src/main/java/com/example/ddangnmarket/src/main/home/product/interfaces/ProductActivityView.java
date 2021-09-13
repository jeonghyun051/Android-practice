package com.example.ddangnmarket.src.main.home.product.interfaces;

import com.example.ddangnmarket.src.main.home.product.models.ProductAnotherResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.home.product.models.Result;

import java.util.ArrayList;

public interface ProductActivityView {

    void validateProductSuccess(boolean isSuccess, int code, String message, Result result);

    void validateProductFailure(String message);

    void validateProductImageSuccess(boolean isSuccess, int code, String message, ArrayList<ProductImageResponse.Result> resultArrayList);

    void validateProductImageFailure(String message);

    void validateProductAnotherSuccess(boolean isSuccess, int code, String message, ArrayList<ProductAnotherResponse.Result> resultArrayList);

    void validateProductAnotherFailure(String message);
}
