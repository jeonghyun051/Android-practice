package com.example.ddangnmarket.src.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.main.category.CategoryFragment;
import com.example.ddangnmarket.src.main.chat.ChatFragment;
import com.example.ddangnmarket.src.main.home.HomeFragment;
import com.example.ddangnmarket.src.main.profile.ProfileFragment;
import com.example.ddangnmarket.src.main.profile.SettingActivity;
import com.example.ddangnmarket.src.main.writing.WritingActivity;
import com.example.ddangnmarket.src.splash.StartActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import static com.example.ddangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity {

    HomeFragment mHomeFragment;
    CategoryFragment mCategoryFragment;
    ChatFragment mChatFragment;
    ProfileFragment mProfileFragment;
    BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mChatFragment = new ChatFragment();
        mProfileFragment = new ProfileFragment();

        moveHome();
    }

    public void moveHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mHomeFragment).commit();
    }

    public void moveCategory() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mCategoryFragment).commit();
    }

    public void moveWriting() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, mWritingFragment).commit();

        if (sSharedPreferences.getString("X-ACCESS-TOKEN", "").equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("회원가입 또는 로그인후 이용할 수 있습니다.");
            builder.setPositiveButton("로그인/가입", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.show();
        } else {
            mBottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            mBottomSheetDialog.setContentView(R.layout.bottom_sheet);
            LinearLayout btnSale = mBottomSheetDialog.findViewById(R.id.bottom_sheet_sale);
            LinearLayout btnAd = mBottomSheetDialog.findViewById(R.id.bottom_sheet_ad);
            btnSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, WritingActivity.class);
                    startActivity(intent);
                    mBottomSheetDialog.dismiss();
                }
            });
            btnAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBottomSheetDialog.dismiss();
                }
            });
            mBottomSheetDialog.show();
        }

    }

    public void moveChat() {
        if (sSharedPreferences.getString("X-ACCESS-TOKEN", "").equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("회원가입 또는 로그인후 이용할 수 있습니다.");
            builder.setPositiveButton("로그인/가입", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.show();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mChatFragment).commit();
        }

    }

    public void moveProfile() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mProfileFragment).commit();
    }

    public void fragmentOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_home:
                moveHome();
                break;
            case R.id.main_btn_category:
                moveCategory();
                break;
            case R.id.main_btn_writing:
                moveWriting();
                break;
            case R.id.main_btn_chat:
                moveChat();
                break;
            case R.id.main_btn_profile:
                moveProfile();
                break;
            default:
                break;
        }
    }
}
