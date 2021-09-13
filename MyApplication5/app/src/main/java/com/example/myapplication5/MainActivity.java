package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUserList;
    private UserAdapter userAdapter;
    private BottomNavigationView bottomNavigationView;


    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<User> users = new ArrayList<>();
        for (int i=1; i<50; i++){
            users.add(new User(i,"user"+i,"titleasdf","오후 14:20"));
        }

        Log.d(TAG, "onCreate: 배열" + users);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvUserList = findViewById(R.id.rv_user_item);
        rvUserList.setLayoutManager(manager);
        userAdapter = new UserAdapter(users);

        rvUserList.setAdapter(userAdapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation_list);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit(); // 최초화면

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.bottom_person:
                    selectedFragment = new SearchFragment();
                    break;

                case R.id.bottom_message:
                    selectedFragment = new MessageFragment();
                    break;

                case R.id.bottom_well:
                    selectedFragment = new WellFragment();
                    break;

                case R.id.bottom_more:
                    selectedFragment = new MoreFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        });


    }
}