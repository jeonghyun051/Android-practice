package com.cos.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Button btnLogout;
    private FirebaseAuth mAuth;
    private TextView tvEmail, tvPassword, tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        tvEmail = findViewById(R.id.tv_email);
        tvPassword = findViewById(R.id.tv_password);
        tvUrl = findViewById(R.id.tv_url);

        FirebaseUser user = mAuth.getCurrentUser(); // 이걸로 유저를 다 찾을 수 있음
        tvEmail.setText(user.getEmail());
        Log.d(TAG, "onCreate: 로그인 사용자" + user.getEmail());
        Log.d(TAG, "onCreate: 로그인 디스플레이네임" + user.getDisplayName()); // 사용자 이름이 나온다

        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        });
    }
}