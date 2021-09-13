package com.cos.daangnchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.cos.daangnchat.Chat.ChatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChat = findViewById(R.id.btn_chat);
        btnChat.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);

        });

    }
}