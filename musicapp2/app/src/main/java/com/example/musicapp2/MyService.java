package com.example.musicapp2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();
    //Binder를 리턴하면 MyService가 가지고 있는 래퍼런스를 사용못함

    //바인더 만들기
    class LocalBinder extends  Binder{
        MediaPlayer gerMp(){
            return  mp;
        }
    }

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.sample1);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //바인드 한다는 건 MainActivity에서 서비스 제어가 가능하기 때문에 여기서 제어할 필요가 없다.
        //IBinder를 만들어서 return 해준다.
        // TODO: Return the communication channel to the service.

        return mBinder;
    }
}