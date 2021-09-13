package com.example.ddangnmarket.src.LocationCertification;

import com.example.ddangnmarket.src.LocationCertification.interfaces.LocationActivityView;
import com.example.ddangnmarket.src.LocationCertification.interfaces.LocationRetrofitInterface;
import com.example.ddangnmarket.src.LocationCertification.models.LocationResponse;
import com.example.ddangnmarket.src.LocationCertification.models.RequestLocation;
import com.example.ddangnmarket.src.login.interfaces.LoginActivityView;
import com.example.ddangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class LocationService {
    private final LocationActivityView locationActivityView;

    public LocationService(LocationActivityView locationActivityView) {
        this.locationActivityView = locationActivityView;
    }

    void postLocation(RequestLocation requestLocation) {
        final LocationRetrofitInterface locationRetrofitInterface = getRetrofit().create(LocationRetrofitInterface.class);
        locationRetrofitInterface.postLocation(requestLocation).enqueue(new Callback<LocationResponse>() {

            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                final LocationResponse locationResponse = response.body();

                locationActivityView.validateLocationSuccess(locationResponse.getIsSuccess(), locationResponse.getCode(), locationResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                locationActivityView.validateLocationFailure(t.getMessage());
            }
        });
    }
}
