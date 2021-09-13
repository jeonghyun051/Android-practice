package com.example.musicapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private MediaPlayer mp;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        String musicNmae = intent.getStringExtra("musicName");

        if (musicNmae.equals("sample1")){
            mp = MediaPlayer.create(MyService.this,R.raw.sample1);
        } else if (musicNmae.equals("howl")){
            mp = MediaPlayer.create(MyService.this,R.raw.howl);
        }

        else{
            mp = MediaPlayer.create(MyService.this,R.raw.sample2);
        }

        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}