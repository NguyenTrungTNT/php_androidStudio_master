package com.example.mypc.musicwithgame.View;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.LoginActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    EditText name, password, email,hobby,age,hero;
    String Name, Password, Email,Hero,Hobby,Age;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        hobby=(EditText)findViewById(R.id.register_hobby);
        age=(EditText)findViewById(R.id.register_age);
        hero=(EditText)findViewById(R.id.register_hero);


    }

    public void register_register(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        Hero = hero.getText().toString();
        Hobby = hobby.getText().toString();
        Age = age.getText().toString();
        if(name.getText().toString().equals("")&&password.getText().toString().equals("")&&age.getText().toString().equals("")&&email.getText().toString().equals("")&&hero.getText().toString().equals("")&&hobby.getText().toString().equals("")){
            Toast.makeText(this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_LONG).show();
        }
        else {
            BackGround b = new BackGround();
            b.execute(Name, Password, Email, Hero, Hobby, Age);
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String email = params[2];
            String hero=params[3];
            String hobby=params[4];
            String age=params[5];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://musicismylife.atspace.cc/Register.php");
                String urlParams = "name="+name+"&password="+password+"&email="+email+"&hero="+hero+"&hobby="+hobby+"&age="+age;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
          /*  if(name.getText().toString().equals("")&&password.getText().toString().equals("")&&age.getText().toString().equals("")&&email.getText().toString().equals("")&&hero.getText().toString().equals("")&&hobby.getText().toString().equals("")){
               s="Vui long nhap du thong tin";
            }*/
            if(s.equals("")){
                s="Data saved successfully.";

            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
}