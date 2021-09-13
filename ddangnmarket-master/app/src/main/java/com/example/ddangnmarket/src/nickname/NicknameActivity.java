package com.example.ddangnmarket.src.nickname;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.login.LoginService;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.nickname.interfaces.NicknameActivityView;
import com.example.ddangnmarket.src.nickname.models.RequestNickname;
import com.example.ddangnmarket.src.main.MainActivity;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class NicknameActivity extends BaseActivity implements NicknameActivityView {
    TextView mTvPhoneNumber;
    EditText mEtNickname;
    Button mBtnStart;
    String mPhoneNumber;
    int mLocationNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mLocationNo = sSharedPreferences.getInt("locationNo",1);
        System.out.println("회원가입 로케이션넘버 : " + mLocationNo);

        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phoneNumber");

        mTvPhoneNumber = findViewById(R.id.nickname_tv_phone_number);
        mTvPhoneNumber.setText(mPhoneNumber);
        mEtNickname = findViewById(R.id.nickname_et_nickname);
        mBtnStart = findViewById(R.id.nickname_btn_start);
    }

    public void nicknameOnClick(View view) {
        switch (view.getId()) {
            case R.id.nickname_btn_start:
                loginStart();
                break;
            default:
                break;
        }
    }

    public void loginStart() {
        NicknameService nicknameService = new NicknameService(this);
        RequestNickname requestNickname = new RequestNickname();
        requestNickname.setPhoneNum(mPhoneNumber);
        requestNickname.setId(mEtNickname.getText().toString());
        requestNickname.setLocation(mLocationNo);
        nicknameService.postNickname(requestNickname);
    }

    public void getJwt() {
        NicknameService nicknameService = new NicknameService(this);
        RequestJwt requestJwt = new RequestJwt();
        requestJwt.setPhoneNum(mPhoneNumber);
        nicknameService.postJwt(requestJwt);
    }

    @Override
    public void validateNicknameSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess && code == 100) {
            showCustomToast(message);
            getJwt();
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateNicknameFailure() {
        showCustomToast("validateNicknameFailure");
    }

    @Override
    public void validateJwtSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result) {
        hideProgressDialog();
        if (isSuccess) {
            if (code == 100) {
                showCustomToast(message);

                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putString("X-ACCESS-TOKEN", result.getJwt());
                editor.putString("nickname", mEtNickname.getText().toString());
                editor.putInt("userNo",result.getUserNo().get(0).getUserNo());
                editor.commit();

                Intent intent = new Intent(NicknameActivity.this, MainActivity.class);
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
        showCustomToast("validateJwtFailure");
    }
}
