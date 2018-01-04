package com.example.mypc.musicwithgame.Service;


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by MyPC on 01/01/2018.
 */

public class Music_Player_Service extends Service{
    static MediaPlayer mPlayer;
    static String song_res;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mPlayer=MediaPlayer.create(getApplicationContext(), Uri.parse(song_res));

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        song_res=intent.getStringExtra("mp3");
        if(mPlayer==null)
        {
            init_mPlayer(song_res);
        }

        mPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mPlayer.release();
        super.onDestroy();
    }

    public void init_mPlayer(String song_url)
    {
        //Declare Media player
        mPlayer = new android.media.MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(song_url);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "Illegal Argument Exception", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "Security Exception", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "Illegal State Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "Illegal State Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Incorrect URL", Toast.LENGTH_LONG).show();
        }
    }
}
