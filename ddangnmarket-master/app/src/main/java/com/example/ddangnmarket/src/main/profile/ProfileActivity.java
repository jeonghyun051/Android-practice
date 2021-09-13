package com.example.ddangnmarket.src.main.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.profile.interfaces.ProfileActivityView;
import com.example.ddangnmarket.src.main.profile.models.ProfileResponse;

import java.util.ArrayList;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {
    ImageView mIvUrl;
    TextView mTvId, mTvAddress, mTvCert, mTvCreatedAt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mIvUrl = findViewById(R.id.profile_iv_url);
        mTvId = findViewById(R.id.profile_tv_id);
        mTvAddress = findViewById(R.id.profile_tv_address);
        mTvCert = findViewById(R.id.profile_tv_cert);
        mTvCreatedAt = findViewById(R.id.profile_tv_createdAt);

        ProfileService profileService = new ProfileService(this);
        int userNo = sSharedPreferences.getInt("userNo",9);
        profileService.getProfile(userNo);
    }

    @Override
    public void validateProfileSuccess(boolean isSuccess, int code, String message, ArrayList<ProfileResponse.Result> results) {
        Glide.with(this).load(results.get(0).getProfileUrl()).into(mIvUrl);
        mTvId.setText(results.get(0).getId());
        mTvAddress.setText(results.get(0).getAddress());
        mTvCert.setText(" "+results.get(0).getCert()+"회 인증");
        mTvCreatedAt.setText(results.get(0).getCreatedAt()+" 가입");
    }

    @Override
    public void validateProfileFailure(String message) {
        showCustomToast(message);
    }
}
