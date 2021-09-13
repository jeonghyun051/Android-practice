package com.example.ddangnmarket.src.main.profile;

import com.example.ddangnmarket.src.main.profile.interfaces.ProfileActivityView;
import com.example.ddangnmarket.src.main.profile.interfaces.ProfileRetrofitInterface;
import com.example.ddangnmarket.src.main.profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ProfileService {

    private final ProfileActivityView profileActivityView;

    public ProfileService(ProfileActivityView profileActivityView) {
        this.profileActivityView = profileActivityView;
    }

    void getProfile(int userNo){
        final ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);
        profileRetrofitInterface.getProfilePath(userNo).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                final ProfileResponse profileResponse = response.body();
                profileActivityView.validateProfileSuccess(profileResponse.getIsSuccess(),profileResponse.getCode(),profileResponse.getMessage(),profileResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileActivityView.validateProfileFailure(t.getMessage());
            }
        });

    }
}
