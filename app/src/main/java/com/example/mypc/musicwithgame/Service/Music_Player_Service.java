package com.example.mypc.musicwithgame.Service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by MyPC on 01/01/2018.
 */

public class Music_Player_Service extends Service{
    static MediaPlayer mediaPlayer;
    static String song_res;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mediaPlayer=MediaPlayer.create(getApplicationContext(), Uri.parse(song_res));

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        song_res=intent.getStringExtra("mp3");

        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
}
