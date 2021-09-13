package com.example.musicapp3;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.SeekBar;

import java.io.Serializable;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();

    public MusicService() {
    }

    //    class LocalBinder extends Binder {
//
//        MusicService getService() {
//            return MusicService.this;
//        }
//    }
    class LocalBinder extends Binder {

        MediaPlayer gerMp() {
            return mp;
        }
    }

        @Override
        public void onCreate() {
            super.onCreate();
            mp = MediaPlayer.create(this, R.raw.sample1);
        }

        public MediaPlayer getMediaPlayer() {
            return mp;
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }

//        @Override
//        public boolean onUnbind(Intent intent) {
//            return super.onUnbind(intent);
//        }
//
//        @Override
//        public void onDestroy() {
//            super.onDestroy();
//        }
    }
