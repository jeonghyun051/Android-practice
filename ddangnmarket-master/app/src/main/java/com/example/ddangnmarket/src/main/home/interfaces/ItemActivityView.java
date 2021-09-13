package com.example.ddangnmarket.src.main.home.interfaces;

import com.example.ddangnmarket.src.main.home.models.ResultProduct;

import java.util.ArrayList;

public interface ItemActivityView {

    void validateItemSuccess(boolean isSuccess, int code, String message, ArrayList<ResultProduct> resultProducts);

    void validateItemFailure();

}
