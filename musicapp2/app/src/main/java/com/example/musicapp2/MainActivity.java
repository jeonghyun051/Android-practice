package com.example.musicapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.example.musicapp2.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private TextInputEditText tiMusic;
    private Button btnStart, btnPause, btnStop;
    private MediaPlayer mp;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: 서비스 연결됨");
            mp = ((MyService.LocalBinder) service).gerMp();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 서비스 실행 = 서비스의 onCreate와 onBind실행 -> connection.onServiceConnected가 콜백됨
        Intent musicIntent = new Intent(MainActivity.this,MyService.class);
        bindService(musicIntent,connection,BIND_AUTO_CREATE);

        tiMusic = findViewById(R.id.ti_music);
        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(v -> {
            // 음악을 재생하는곳
            mp.start();
        });

        // 서비스 종료
        btnStop.setOnClickListener(v -> {
            mp.pause(); // stop을 쓰지 말것 레퍼런스를 날려버림
            mp.seekTo(0);
        });

        btnPause.setOnClickListener(v -> {
            mp.pause();
        });
    }
}