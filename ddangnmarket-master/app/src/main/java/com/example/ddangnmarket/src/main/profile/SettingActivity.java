package com.example.ddangnmarket.src.main.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.splash.StartActivity;

import static com.example.ddangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class SettingActivity extends AppCompatActivity {
    TextView mTvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mTvLogout = findViewById(R.id.setting_tv_logout);
        mTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //SharedPreferences sharedPreferences = getSharedPreferences("X-ACCESS-TOKEN", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        editor.putString(X_ACCESS_TOKEN, "");
                        editor.commit();

                        Intent intent = new Intent(SettingActivity.this, StartActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
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
    }
}
