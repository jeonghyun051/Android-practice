package com.example.ddangnmarket.src.nickname;

import com.example.ddangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.nickname.interfaces.NicknameActivityView;
import com.example.ddangnmarket.src.nickname.interfaces.NicknameRetrofitInterface;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.nickname.models.RequestNickname;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class NicknameService {
    private final NicknameActivityView mNicknameActivityView;

    NicknameService(final NicknameActivityView nocknameActivityView) {
        this.mNicknameActivityView = nocknameActivityView;
    }

    void postNickname(RequestNickname requestNickname) {
        final NicknameRetrofitInterface nicknameRetrofitInterface = getRetrofit().create(NicknameRetrofitInterface.class);
        nicknameRetrofitInterface.postNickname(requestNickname).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                mNicknameActivityView.validateNicknameSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mNicknameActivityView.validateNicknameFailure();
            }
        });
    }

    void postJwt(RequestJwt requestJwt) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postJwt(requestJwt).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mNicknameActivityView.validateJwtFailure();
                    return;
                }
                mNicknameActivityView.validateJwtSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage(), loginResponse.getResult());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mNicknameActivityView.validateJwtFailure();
            }
        });
    }
}
