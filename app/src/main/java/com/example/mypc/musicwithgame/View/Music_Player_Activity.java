package com.example.mypc.musicwithgame.View;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mypc.musicwithgame.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Music_Player_Activity extends AppCompatActivity {

    static MediaPlayer mPlayer;
    ImageButton buttonPlay, buttonStop, buttonPause, buttonNext, buttonPrev, buttonMute;
    TextView song_name, bpm, status;
    ImageView song_img,image;
    SeekBar seekBar;
    String title_res, image_res, song_res;
    Boolean sound_stat = true;

    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    public static int oneTimeOnly = 0;
    int current_pos = 0;
    int dur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__player_);

        Intent i=getIntent();
        HashMap<String, String> sel_song = (HashMap<String, String>) i.getSerializableExtra("sel_song");
        title_res = sel_song.get("title");
        image_res = sel_song.get("image");
        song_res = sel_song.get("mp3");

        buttonPlay = (ImageButton) findViewById(R.id.play);
        buttonStop = (ImageButton) findViewById(R.id.stop);
        buttonPause = (ImageButton) findViewById(R.id.pause);
        buttonNext = (ImageButton) findViewById(R.id.next);
        buttonPrev = (ImageButton) findViewById(R.id.previous);
        buttonMute = (ImageButton) findViewById(R.id.mute);
        song_name = (TextView) findViewById(R.id.song_name);
        status = (TextView) findViewById(R.id.status);
        bpm = (TextView) findViewById(R.id.song_bpm);
        song_img = (ImageView) findViewById(R.id.song_image);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        image=(ImageView)findViewById(R.id.image);
        seekBar.setClickable(false);


        song_name.setText(title_res);
        Glide.with(this).load(image_res).into(image);/*
        bpm.setText(song_bpm);
*/
        try {

            URL myFileUrl = new URL (image_res);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
          /*  Drawable icon=new BitmapDrawable(is);
            song_img.setImageDrawable(icon);*/

            song_img.setImageBitmap(BitmapFactory.decodeStream(is));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        init_mPlayer(song_res);


        //Set Seekbar
        dur = mPlayer.getDuration();
        seekBar.setMax(dur);

    }


    public void onStop(View v)
    {
        if(mPlayer!=null && mPlayer.isPlaying()){

            mPlayer.pause();
            current_pos = 0;
            seekBar.setProgress((int)current_pos);
            buttonStop.setEnabled(false);
            buttonPlay.setEnabled(true);
            buttonPause.setEnabled(false);
            buttonNext.setEnabled(false);
            buttonPrev.setEnabled(false);
            status.setText("Stop");

        }

    }

    public void onPlay(View v)
    {

        if(mPlayer==null)
        {
            init_mPlayer(song_res);
        }

        mPlayer.seekTo(current_pos);
        mPlayer.start();
        current_pos = mPlayer.getCurrentPosition();
        seekBar.setProgress((int)current_pos);
        myHandler.postDelayed(UpdateSongTime, 100);

        buttonPause.setEnabled(true);
        buttonPlay.setEnabled(false);
        buttonStop.setEnabled(true);
        buttonNext.setEnabled(true);
        buttonPrev.setEnabled(true);
        status.setText("Play");

    }

    public void onPause(View v)
    {

        if(mPlayer!=null && mPlayer.isPlaying()){


            mPlayer.pause();
            current_pos = mPlayer.getCurrentPosition();
            buttonPause.setEnabled(false);
            buttonPlay.setEnabled(true);
            buttonStop.setEnabled(false);
            buttonNext.setEnabled(false);
            buttonPrev.setEnabled(false);
            status.setText("Pause");

        }

    }

    public void onNext(View v)
    {
        if(mPlayer!=null) {
            current_pos = current_pos + 5000;
            mPlayer.seekTo(current_pos);
            mPlayer.start();
            current_pos = mPlayer.getCurrentPosition();
            seekBar.setProgress((int) current_pos);
        }

    }

    public void onPrevious(View v)
    {
        if(mPlayer!=null) {
            current_pos = current_pos - 5000;
            mPlayer.seekTo(current_pos);
            mPlayer.start();
            current_pos = mPlayer.getCurrentPosition();
            seekBar.setProgress((int)current_pos);
        }
    }

    public void onMute(View v)
    {
        if(mPlayer!=null) {
            if (sound_stat == true) {
                mPlayer.setVolume(0, 0);
                buttonMute.setImageResource(R.drawable.mute);
                sound_stat = false;

            } else {
                mPlayer.setVolume(0.4f, 0.4f);
                buttonMute.setImageResource(R.drawable.sound);
                sound_stat = true;
            }
        }


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

    protected void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mPlayer.getCurrentPosition();
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };


    //Stop the Handler when back button is pressed to avoid errors
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        // Toast.makeText(getApplicationContext(),"Going Back",Toast.LENGTH_LONG).show();
        if(mPlayer!=null) {
            myHandler.removeCallbacks(UpdateSongTime);
        }


    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(mPlayer!=null && !mPlayer.isPlaying()){
            buttonPlay.performClick();
        }
    }


    @Override
    public void onStop()
    {
        super.onStop();

    }




}


