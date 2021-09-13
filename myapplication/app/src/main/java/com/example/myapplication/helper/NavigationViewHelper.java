package com.example.myapplication.helper;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class NavigationViewHelper {
    private static final String TAG = "NavigationViewHelper";

    public static void enable(Context context, NavigationView view){
        view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.item1){

                Log.d(TAG, "enable: 아이템1 눌려짐");
            }
            return false;
        });
    }
}



