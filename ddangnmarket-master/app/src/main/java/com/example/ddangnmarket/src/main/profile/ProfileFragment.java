package com.example.ddangnmarket.src.main.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.LocationCertification.LocationCertificationActivity;
import com.example.ddangnmarket.src.Profile.Profile;
import com.example.ddangnmarket.src.location.LocationActivity;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.main.profile.interfaces.ProfileActivityView;
import com.example.ddangnmarket.src.main.profile.models.ProfileResponse;
import com.example.ddangnmarket.src.splash.StartActivity;

import java.util.ArrayList;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class ProfileFragment extends Fragment implements ProfileActivityView {
    MainActivity activity;
    LinearLayout mBtnLogin, mTvSetting, mBtnLocation, mBtnChangeLocation;
    TextView mTvNickname, mTvLocation;
    ImageView mIvSetting, mIvChange;
    Button mBtnMyProfile;
    int mLocationNo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mBtnLogin = view.findViewById(R.id.profile_btn_login);
        mTvNickname = view.findViewById(R.id.profile_tv_nickname);
        mTvLocation = view.findViewById(R.id.profile_tv_location);
        mTvSetting = view.findViewById(R.id.profile_tv_setting);
        mIvSetting = view.findViewById(R.id.profile_iv_setting);
        mBtnMyProfile = view.findViewById(R.id.profile_btn_my_profile);
        mBtnLocation = view.findViewById(R.id.profile_btn_location);
        mBtnChangeLocation = view.findViewById(R.id.profile_btn_change_location);
        mIvChange = view.findViewById(R.id.profile_iv_change_image);

        mIvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSettingActivity();
            }
        });
        mTvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSettingActivity();
            }
        });

        String jwt = sSharedPreferences.getString("X-ACCESS-TOKEN", "");
        String nickname = sSharedPreferences.getString("nickname", "");
        String dong = sSharedPreferences.getString("dong", "");

        if (jwt.equals("")) {
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("회원가입 또는 로그인후 이용할 수 있습니다.");
                    builder.setPositiveButton("로그인/가입", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            moveLoginActivity();
                        }
                    });

                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
            });
        } else {
            ProfileService profileService = new ProfileService(this);
            int userNo = sSharedPreferences.getInt("userNo",9);
            profileService.getProfile(userNo);

            mBtnLogin.setEnabled(false);
//            mTvNickname.setText(nickname);
//            mTvLocation.setText(dong);
            mBtnMyProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ProfileActivity.class);
                    startActivity(intent);
                }
            });
        }

        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LocationCertificationActivity.class);
                startActivity(intent);
            }
        });

        mBtnChangeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LocationActivity.class);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putString("changeLocation", "true");
                editor.commit();
                startActivity(intent);
            }
        });

        mIvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Profile.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void moveLoginActivity() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    public void moveSettingActivity() {
        Intent intent = new Intent(activity, SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void validateProfileSuccess(boolean isSuccess, int code, String message, ArrayList<ProfileResponse.Result> results) {
        mTvNickname.setText(results.get(0).getId());
        mTvLocation.setText(results.get(0).getAddress());
        Glide.with(getContext()).load(results.get(0).getProfileUrl()).into(mIvChange);
        mIvChange.setBackground(new ShapeDrawable(new OvalShape()));
        mIvChange.setClipToOutline(true);
        mIvChange.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void validateProfileFailure(String message) {

    }
}
