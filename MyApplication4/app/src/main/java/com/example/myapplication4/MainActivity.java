package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import info.androidhive.fontawesome.FontDrawable;

public class MainActivity extends AppCompatActivity {

    private ImageView ivHeart;
    private boolean isLike = false;
    private BottomNavigationView bottomNavigationView;

    Integer num =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit(); // 최초화면

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.bottom_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.bottom_setting:
                    selectedFragment = new SettingsFragment();
                    break;

                case R.id.bottom_menu:
                    selectedFragment = new NavigationFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        });
    }
}