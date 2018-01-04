package com.example.mypc.musicwithgame.View;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mypc.musicwithgame.Model.CARS;
import com.example.mypc.musicwithgame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rate_Activity extends AppCompatActivity {
    static List<Integer> v;
    static int[][] Matrix =new int[10][10];
    static int[][] doTuongDong;
    static TextView tvRate,tvUser,tvBaiHat;
    ListView listview;
    private ArrayList<HashMap<String, String>> array;
    static String rate,user,bai_hat,nguCanh;
    static ArrayList<CARS> carsArrayList;
    static String Ma_BAI_HAT,BAN_NHAC_SERVER,NGU_CANH_SERVER,RATE_SERVER,USER_SERVER;
    static String CARS_res="http://musicismylife.atspace.cc/CARS.php";
    static SimpleAdapter adapter;

    public static final String USER = "user";
    public static final String BAI_HAT = "bai_hat";
    public static final String RATE = "rate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_);

        Intent i=getIntent();
        nguCanh=i.getStringExtra("nguCanh");

        ListView lv = (ListView) findViewById(R.id.lv);

        sentDataToServer sent=new sentDataToServer();
        sent.execute(nguCanh);
        LoadData();

// init adapter
        // mang from chua cac tag se lay du lieu trong mang
        String[] from = { USER, BAI_HAT, RATE };
        // mag to chua cac id cua view se duoc gan boi cac tag tren
        int[] to = { R.id.ivIcon, R.id.tvName, R.id.tvPopular };

// khi init SimpleAdapter cac param truyen vao gom co
        adapter = new SimpleAdapter(this, // context
                array,// mang du lieu se hien thi len listview
                R.layout.demo_item,// layout 1 item cua listview
                from, // lay du lieu trong mang theo cac tag
                to // lay du lieu theo cac tag gan cho cac view voi id tuong ung
        );


        lv.setAdapter(adapter);


        tvUser=(TextView)findViewById(R.id.tvUser);


/*
        tvBaiHat.setText(user);

        tvUser.setText(user);
        tvRate.setText(user);*/






    }


    private void LoadData() {
        // TODO Auto-generated method stub

        // trong vi du nay minh tu khoi tao mot mang du lieu va hien thi len listview
        // thuc te mang data co the load tu server hoac lay tu cac provider khac

        array = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp1 = new HashMap<String, String>();
        temp1.put(USER, String.valueOf(R.drawable.ic_chat_white_24dp));
        temp1.put(BAI_HAT, "CANADA");
        temp1.put(RATE, "100000000");
        array.add(temp1);

        HashMap<String, String> temp2 = new HashMap<String, String>();
        temp2.put(USER, String.valueOf(R.drawable.ic_chat_white_24dp));
        temp2.put(BAI_HAT, "GB");
        temp2.put(RATE, "200000000");
        array.add(temp2);



    }

    public  int[][] Matrix(){
        Context ctx = getApplicationContext();
        String str = readFileTxtFromAssets(ctx, "cars.txt");
        String [] S= str.split("[ ]");
        for (int j=0;j<10;j++)
        {
            int a = Integer.valueOf(S[j]);

          Matrix[j][1]= a;


        }
        return Matrix;
    }


    public static String readFileTxtFromAssets(Context context, String fileName) {
        String strLine, strContent ="";
        try{
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            InputStreamReader inStrR = new InputStreamReader(inputStream);
            BufferedReader buffreader = new BufferedReader(inStrR);
            while (( strLine = buffreader.readLine()) != null) {
                strContent += strLine + "  ";
            }
            inputStream.close();
        }catch (IOException e) {
            return null;
        }
        return strContent;
    }

    public List<Integer> Init_Similarity_Vector(){
        int[][] mtx= Matrix();

        for(int i=0;i<mtx.length;i++){
            for (int j=0;i<mtx.length;j++){
                if(mtx[i][j]!=-1&&mtx[i][j+1]!=-1){

                    v.add(mtx[i][j]);
                    v.add(mtx[i][j+1]);


                }

            }
        }

        return v;
    }

    public static int[][] TyLeTuongDong(int[][] matrix){

        Integer v1=v.get(1);
        Integer v2=v.get(2);

        return doTuongDong;

    }


    class sentDataToServer extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String nguCanh = params[0];
            String data="";
            int tmp;

            try {
                URL url = new URL(CARS_res);
                String urlParams = "nguCanh="+nguCanh;

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
                JSONArray jsonArray = root.getJSONArray("cars");
                array = new ArrayList<HashMap<String, String>>();

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject cars=jsonArray.getJSONObject(i);

                    Ma_BAI_HAT = cars.getString("maBanNhac");
                    USER_SERVER = cars.getString("user");
                    BAN_NHAC_SERVER = cars.getString("banNhac");
                    NGU_CANH_SERVER = cars.getString("nguCanh");
                    RATE_SERVER = cars.getString("rate");

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(USER, String.valueOf(R.drawable.ic_chat_white_24dp));
                    map.put(BAI_HAT, BAN_NHAC_SERVER);
                    map.put(RATE, RATE_SERVER);
                    array.add(map);



                }




            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }


        }
    };

}
