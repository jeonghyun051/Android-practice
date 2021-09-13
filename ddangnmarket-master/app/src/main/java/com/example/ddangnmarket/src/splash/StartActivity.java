package com.example.ddangnmarket.src.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.location.LocationActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void intentToLocationActivity(){
        Intent intent = new Intent(StartActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }

    public void startOnClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn_set_location:
                intentToLocationActivity();
                break;
            default:
                break;
        }
    }
}
