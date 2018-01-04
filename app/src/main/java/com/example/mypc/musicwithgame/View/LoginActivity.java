package com.example.mypc.musicwithgame.View;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mypc.musicwithgame.Fragment.AccountFragment;
import com.example.mypc.musicwithgame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText name, password;
    String Name, Password;
    Context ctx=this;
    ImageView imageView;
    String NAME=null, PASSWORD=null, EMAIL=null,AGE=null,HOBBY=null,HERO=null,ID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);

    }

    public void main_register(View v){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void main_login(View v){

        Name = name.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();

        b.execute(Name, Password);


    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://musicismylife.atspace.cc/Login.php");
                String urlParams = "name="+name+"&password="+password;

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
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                ID=user_data.getString("id");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");
                HERO=user_data.getString("hero");
                HOBBY=user_data.getString("hobby");
                AGE=user_data.getString("age");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
            if(name.getText().toString().equals(NAME) && password.getText().toString().equals(PASSWORD)){

//correcct password
                Intent i = new Intent(ctx, MainActivity.class);
                i.putExtra("id", ID);
                i.putExtra("name", NAME);
                i.putExtra("password", PASSWORD);
                i.putExtra("email", EMAIL);
                i.putExtra("age",AGE);
                i.putExtra("hobby",HOBBY);
                i.putExtra("hero",HERO);
                i.putExtra("err", err);

                startActivity(i);
            }
            else{
//wrong password
                Toast.makeText(LoginActivity.this,"Sai thông tin đăng nhập !",Toast.LENGTH_LONG).show();
            }

                }
            };



}

