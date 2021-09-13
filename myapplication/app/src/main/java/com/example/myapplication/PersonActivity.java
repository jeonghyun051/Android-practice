package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PersonActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private ImageView IvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        toolbarMain = findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbarMain);

        IvBack = findViewById(R.id.iv_back);

        IvBack.setOnClickListener(v -> {

            finish();
//            Intent intent = new Intent(PersonActivity.this,MainActivity.class);
//            startActivity(intent);

        });
    }
}