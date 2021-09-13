package com.example.ddangnmarket.src.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.MainActivity;

import static com.example.ddangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity {

    Thread mSplashTread;
    ImageView mIvDaangnMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIvDaangnMarket = findViewById(R.id.splash_iv_daangn_market);

        mSplashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    //SharedPreferences sharedPreferences = getSharedPreferences("X-ACCESS-TOKEN",MODE_PRIVATE);
                    String jwt = sSharedPreferences.getString(X_ACCESS_TOKEN,"");
                    System.out.println("sharedpreferences : " + jwt);
                    if(jwt.equals("")) {
                        Intent intent = new Intent(SplashActivity.this, StartActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }else{
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        mSplashTread.start();
    }

}

