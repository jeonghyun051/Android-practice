package com.example.ddangnmarket.src.LocationCertification.interfaces;

public interface LocationActivityView {

    void validateLocationSuccess(boolean isSuccess, int code, String message);

    void validateLocationFailure(String message);
}
