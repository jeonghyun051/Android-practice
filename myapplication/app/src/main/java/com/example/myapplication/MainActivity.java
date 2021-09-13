package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.helper.NavigationViewHelper;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private Toolbar toolbarMain;
    private ImageView IvPerson;
    private DrawerLayout drawer;
    private ImageView ivmenu;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // toolbarMain = findViewById(R.id.toolbar_main);
        //setSupportActionBar(toolbarMain);

        IvPerson = findViewById(R.id.iv_person);
        IvPerson.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,PersonActivity.class);
            startActivity(intent);

        });

        drawer = findViewById(R.id.drawer);
        ivmenu = findViewById(R.id.iv_menu);
        ivmenu.setOnClickListener(v -> {
            drawer.openDrawer(GravityCompat.START);
        });

        nv = findViewById(R.id.nv);

        NavigationViewHelper.enable(MainActivity.this,nv);

    }
}
