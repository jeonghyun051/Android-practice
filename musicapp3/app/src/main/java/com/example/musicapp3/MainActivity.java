package com.example.musicapp3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// 참고 : https://android-kr.tistory.com/283
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainLog";

    // View
    private ImageView btn_play_stop;
    private SeekBar seekBar;
    private TextView tvTime;

    // 음악 관련
    private int isPlaying = -1; // 1은 음악재생, -1은 음악멈춤
    private MusicService musicService;
    private MediaPlayer mp;

    // 쓰레드 관련
    Handler handler = new Handler();
    private Thread uiHandleThread;
    private boolean threadStatus = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 서비스가 가지고 있는 binder를 전달 받기
//            MusicService.LocalBinder mb = (MusicService.LocalBinder) service;
//            musicService = mb.getService();
//
//            mp = musicService.getMediaPlayer();
//            seekBarInit();

            mp = ((MusicService.LocalBinder) service).gerMp();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop();
            mp.release();
        }
    };

    public void seekBarInit(){
        seekBar.setMax(mp.getDuration());
        seekBar.setProgress(0);
    }

    public void musicStart(){
        mp.start();
        btn_play_stop.setImageResource(R.drawable.ic_pause);
    }

    public void musicPause(){
        mp.pause();
        btn_play_stop.setImageResource(R.drawable.ic_play);
    }

    public void musicStop(){
        mp.seekTo(0);
        btn_play_stop.setImageResource(R.drawable.ic_play);
        seekBar.setProgress(0);
        threadStatus = true;
        isPlaying = -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btn_play_stop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);

        // 서비스 바인딩 하기
        Intent musicIntent = new Intent(MainActivity.this, MusicService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);


        btn_play_stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isPlaying = isPlaying * -1;
                if (isPlaying == 1) {
                    musicStart();
                } else {
                    musicPause();
                }

                uiHandleThread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (isPlaying == 1) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar.setProgress(mp.getCurrentPosition());

                                    if (mp.getCurrentPosition() >= mp.getDuration()) {
                                        musicStop();
                                    }
                                }
                            });

                            try {
                                Thread.sleep(1000);
                                if(threadStatus){
                                    uiHandleThread.interrupt();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

//                            boolean status = uiHandleThread.isInterrupted();
//                            if(status){
//                                Log.d(TAG, "run: 쓰레드 종료");
//                                break;
//                            }else{
//                                Log.d(TAG, "run: 쓰레드 종료되지 않음.");
//                            }

                        }
                    }
                });
                uiHandleThread.start();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // 유저가 SeekBar를 클릭할 때
                if (fromUser) {
                    mp.seekTo(progress);
                }

                int m = progress / 60000;
                int s = (progress % 60000) / 1000;
                String strTime = String.format("%02d:%02d", m, s);
                tvTime.setText(strTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

    }
}
