package com.cos.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate는 최초에 한번
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() { // onStart는 onCreate 다음으로 실행되는데 예를들어 홈버튼을 눌러서 앱을 다시 시작할때도 호출된다.
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        
        if (currentUser != null){

            Log.d(TAG, "onStart: email :" + currentUser.getEmail());
            Log.d(TAG, "onStart: uid :" + currentUser.getUid());
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }else{
            Log.d(TAG, "onStart: 로그인 되지 않음");
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}