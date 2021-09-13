package com.example.ddangnmarket.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.interfaces.LoginActivityView;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.login.models.RequestMessage;
import com.example.ddangnmarket.src.login.models.RequestPhoneCert;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.nickname.NicknameActivity;

import static com.example.ddangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText mEtPhoneNumber, mEtPutCert;
    Button mBtnGetCert, mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mEtPhoneNumber = findViewById(R.id.login_et_phone_number);
        mEtPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mEtPutCert = findViewById(R.id.login_et_put_cert);
        mBtnGetCert = findViewById(R.id.login_btn_get_cert);
        mBtnStart = findViewById(R.id.login_btn_start);
    }

    public void loginOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_iv_back:
                onBackPressed();
                break;
            case R.id.login_btn_get_cert:
                getCert();
                break;
            case R.id.login_btn_start:
                loginStart();
                break;
            default:
                break;
        }
    }

    public void getCert() {
        showProgressDialog();
        LoginService loginService = new LoginService(this);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setPhoneNum(mEtPhoneNumber.getText().toString());
        loginService.postPhone(requestMessage);
    }

    public void loginStart() {
        showProgressDialog();
        LoginService loginService = new LoginService(this);
        RequestPhoneCert requestPhoneCert = new RequestPhoneCert();
        requestPhoneCert.setPhoneNum(mEtPhoneNumber.getText().toString());
        requestPhoneCert.setCertNo(Integer.parseInt(mEtPutCert.getText().toString()));
        loginService.postCert(requestPhoneCert);
    }

    public void getJwt() {
        LoginService loginService = new LoginService(this);
        RequestJwt requestJwt = new RequestJwt();
        requestJwt.setPhoneNum(mEtPhoneNumber.getText().toString());
        loginService.postJwt(requestJwt);
    }

    @Override
    public void validateMessageSuccess(boolean isSuccess, int code, String message) {
        hideProgressDialog();
        if (isSuccess == true && code == 100) {
            showCustomToast(message);
        } else if (isSuccess == false && code == 200) {
            showCustomToast(message);
        }
    }

    @Override
    public void validateMessageFailure() {
        hideProgressDialog();
        showCustomToast("validateMessageFailure");
    }

    @Override
    public void validatePhoneCertSuccess(boolean isSuccess, int code, String message) {
        System.out.println(isSuccess + " " + code + " " + message);
        hideProgressDialog();
        if (isSuccess) {
            String phoneNumber = mEtPhoneNumber.getText().toString();
            if (code == 100) {
                getJwt();
            } else if (code == 101) {
                showCustomToast(message);

                Intent intent = new Intent(LoginActivity.this, NicknameActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
                finish();
            }
        } else
            showCustomToast(message);
    }

    @Override
    public void validatePhoneCertFailure() {
        hideProgressDialog();
        showCustomToast("validatePhoneCertFailure");
    }

    @Override
    public void validateJwtSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result) {
        hideProgressDialog();
        if (isSuccess) {
            if (code == 100) {
                showCustomToast(message);

                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putString(X_ACCESS_TOKEN,result.getJwt());
                editor.putInt("userNo",result.getUserNo().get(0).getUserNo());
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else if (code == 200) {
                showCustomToast(message);
            }
        } else
            showCustomToast(message);
    }

    @Override
    public void validateJwtFailure() {
        hideProgressDialog();
        showCustomToast("validateJwtFailure");
    }
}
