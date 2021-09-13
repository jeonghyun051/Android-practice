package com.example.myapplication5;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * PagerAdapter는 얖영에 page를 미리 띄워둔다.
 * FragmentPagerAdapter는 한번 뜬 객체는 지우지 않는다. 적으면 얘를 항상 쓰면 된다.
 * FragmentPagerStateAdapter 화면에서 보이지 않으면 날린다.
 */

public class MyFragMentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment = new ArrayList<>();

    public MyFragMentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment){
        mFragment.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
