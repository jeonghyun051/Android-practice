package com.example.ddangnmarket.src.Profile;

import com.example.ddangnmarket.src.Profile.interfaces.ProfileActivityView;
import com.example.ddangnmarket.src.Profile.interfaces.ProfileRetrofitInterface;
import com.example.ddangnmarket.src.Profile.models.ProfileResponse;
import com.example.ddangnmarket.src.Profile.models.RequestProfile;
import com.example.ddangnmarket.src.login.interfaces.LoginActivityView;
import com.example.ddangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ProfileService {

    private final ProfileActivityView profileActivityView;

    public ProfileService(ProfileActivityView profileActivityView) {
        this.profileActivityView = profileActivityView;
    }

    void patchProfile(RequestProfile requestProfile) {
        final ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);
        profileRetrofitInterface.patchProfile(requestProfile).enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                final ProfileResponse profileResponse = response.body();
                profileActivityView.validateProfileSuccess(profileResponse.isSuccess(), profileResponse.getCode(), profileResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileActivityView.validateProfileFailure();
            }
        });
    }
}
