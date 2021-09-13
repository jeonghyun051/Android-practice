package com.example.ddangnmarket.src.main.writing.interfaces;

import com.example.ddangnmarket.src.main.writing.models.Result;

public interface WritingActivityView {

        void validateProductSuccess(boolean isSuccess, int code, String message, Result result);

        void validateProductFailure(String message);

        void validateProductImageSuccess(boolean isSuccess, int code, String message, int index);

        void validateProductImageFailure(String message);

}
