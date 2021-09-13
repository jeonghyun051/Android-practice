package com.example.ddangnmarket.src.login;

import com.example.ddangnmarket.src.login.interfaces.LoginActivityView;
import com.example.ddangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.login.models.RequestMessage;
import com.example.ddangnmarket.src.login.models.RequestPhoneCert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postPhone(RequestMessage requestMessage) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postPhone(requestMessage).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validateMessageFailure();
                    return;
                }
                mLoginActivityView.validateMessageSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateMessageFailure();
            }
        });
    }

    void postCert(RequestPhoneCert requestPhoneCert) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postCert(requestPhoneCert).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validatePhoneCertFailure();
                    return;
                }
                mLoginActivityView.validatePhoneCertSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validatePhoneCertFailure();
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
                    mLoginActivityView.validateJwtFailure();
                    return;
                }
                mLoginActivityView.validateJwtSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage(), loginResponse.getResult());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateJwtFailure();
            }
        });
    }
}

