package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private MyFragMentPagerAdapter myFragMentPagerAdapter;
    private ViewPager vpContainer;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TabLayout은 메뉴 xml이 없음
        vpContainer = findViewById(R.id.vp_container);
        tabs = findViewById(R.id.tabs);

        myFragMentPagerAdapter = new MyFragMentPagerAdapter(getSupportFragmentManager(),1);

        myFragMentPagerAdapter.addFragment(new Frag1());
        myFragMentPagerAdapter.addFragment(new Frag2());
        myFragMentPagerAdapter.addFragment(new Frag3());

        vpContainer.setAdapter(myFragMentPagerAdapter);

        // tab이랑 연결되어야 함.
        tabs.setupWithViewPager(vpContainer);

        // tab에 아이템 그리기
        tabs.getTabAt(0).setIcon(R.drawable.ic_launcher_background);
        tabs.getTabAt(1).setText("2");
        tabs.getTabAt(2).setText("3");
    }
}