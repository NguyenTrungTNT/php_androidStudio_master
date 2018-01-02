package com.example.mypc.musicwithgame.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.mypc.musicwithgame.R;

public class Rate_Activity extends AppCompatActivity {
    static TextView tvRate,tvUser,tvBaiHat;
    static String rate,user,bai_hat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_);

        Intent i=getIntent();
        user=i.getStringExtra("user");
        rate=i.getStringExtra("rate");
        bai_hat=i.getStringExtra("ban_nhac");

        tvRate=(TextView)findViewById(R.id.tvRate);
        tvUser=(TextView)findViewById(R.id.tvUser);
        tvBaiHat=(TextView)findViewById(R.id.tvBaiHat);

        tvBaiHat.setText(bai_hat);
        tvUser.setText(user);
        tvRate.setText(rate);





    }

}
