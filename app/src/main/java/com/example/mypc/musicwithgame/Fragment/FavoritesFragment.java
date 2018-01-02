package com.example.mypc.musicwithgame.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mypc.musicwithgame.Model.CARS;
import com.example.mypc.musicwithgame.Model.MyData;
import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.LoginActivity;
import com.example.mypc.musicwithgame.View.MainActivity;
import com.example.mypc.musicwithgame.View.Rate_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FavoritesFragment extends Fragment {

    static String nhanVat,tamTrang,thoiGian;
    static int Ma_BAI_HAT,BAN_NHAC,USER,RATE,NGU_CANH_SERVER;
    static int NGU_CANH;
    static String CARS="http://musicismylife.atspace.cc/CARS.php";
    static Spinner spnDiaDiem,spnTamTrang,spnChoiCungAi,spnThoiGian,spnNhanVat;
    static ArrayList<CARS> carsArrayList;
    static Button submit;
    static TextView tv;
    static String a="abc";

    CARS cars;


    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_favorites,container,false);

        spnDiaDiem=(Spinner)v.findViewById(R.id.spnĐiaDiem);
        spnTamTrang=(Spinner)v.findViewById(R.id.spnTamTrang);
        spnChoiCungAi=(Spinner)v.findViewById(R.id.spnChoiCungAi);
        spnThoiGian=(Spinner)v.findViewById(R.id.spnThoiGian);
        spnNhanVat=(Spinner)v.findViewById(R.id.spnNhanVat);
        submit=(Button)v.findViewById(R.id.bntSubmit) ;
        tv=(TextView)v.findViewById(R.id.tve) ;

        sentDataToServer sent=new sentDataToServer();
        sent.execute(NGU_CANH);


       for (int i=0;i<carsArrayList.size();i++){
         cars=carsArrayList.get(2);
         //int a=cars.getUser();

       }


        Spiner_Manager();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();
                int nc=truongHopNguCanh();


                Toast.makeText(getActivity(),NGU_CANH+"user:"+USER+"Bản nhạc:"+BAN_NHAC+"rate:"+RATE,Toast.LENGTH_LONG).show();



            }
        });


        return v;
    }



    public int truongHopNguCanh() {
        getText();

        if (nhanVat.equals("Tank") && tamTrang.equals("Buồn") && thoiGian.equals("Sáng")) {
            NGU_CANH = 1;
        }

        if (nhanVat.equals("Tank") && tamTrang.equals("Buồn") && thoiGian.equals("Chiều")) {
            NGU_CANH = 2;
        }

        if (nhanVat.equals("Tank") && tamTrang.equals("Buồn") && thoiGian.equals("Tối")) {
            NGU_CANH = 3;
        }

        if (nhanVat.equals("Tank") && tamTrang.equals("Vui") && thoiGian.equals("Sáng")) {
            NGU_CANH = 4;
        }

        if (nhanVat.equals("Tank") && tamTrang.equals("Vui") && thoiGian.equals("Chiều")) {
            NGU_CANH = 5;
        }

        if (nhanVat.equals("Tank") && tamTrang.equals("Vui") && thoiGian.equals("Tối")) {
            NGU_CANH = 6;
        }

        //Support

        if (nhanVat.equals("Support") && tamTrang.equals("Buồn") && thoiGian.equals("Sáng")) {
            NGU_CANH = 7;
        }

        if (nhanVat.equals("Support") && tamTrang.equals("Buồn") && thoiGian.equals("Chiều")) {
            NGU_CANH = 8;
        }

        if (nhanVat.equals("Support") && tamTrang.equals("Buồn") && thoiGian.equals("Tối")) {
            NGU_CANH = 9;
        }

        if (nhanVat.equals("Support") && tamTrang.equals("Vui") && thoiGian.equals("Sáng")) {
            NGU_CANH = 10;
        }

        if (nhanVat.equals("Support") && tamTrang.equals("Vui") && thoiGian.equals("Chiều")) {
            NGU_CANH = 11;
        }

        if (nhanVat.equals("Support") && tamTrang.equals("Vui") && thoiGian.equals("Tối")) {
            NGU_CANH = 12;
        }

        //Carry

        if (nhanVat.equals("Carry") && tamTrang.equals("Buồn") && thoiGian.equals("Sáng")) {
            NGU_CANH = 13;
        }

        if (nhanVat.equals("Carry") && tamTrang.equals("Buồn") && thoiGian.equals("Chiều")) {
            NGU_CANH = 14;
        }

        if (nhanVat.equals("Carry") && tamTrang.equals("Buồn") && thoiGian.equals("Tối")) {
            NGU_CANH = 15;
        }

        if (nhanVat.equals("Carry") && tamTrang.equals("Vui") && thoiGian.equals("Sáng")) {
            NGU_CANH = 16;
        }

        if (nhanVat.equals("Carry") && tamTrang.equals("Vui") && thoiGian.equals("Chiều")) {
            NGU_CANH = 17;
        }

        if (nhanVat.equals("Carry") && tamTrang.equals("Vui") && thoiGian.equals("Tối")) {
            NGU_CANH = 18;
        }
        return NGU_CANH;
    }


    public void getText(){
        nhanVat=spnNhanVat.getSelectedItem().toString();
        thoiGian=spnThoiGian.getSelectedItem().toString();
        tamTrang=spnTamTrang.getSelectedItem().toString();
    }

    public void Spiner_Manager(){
        List<String> listDiaDiem=new ArrayList<>();

        listDiaDiem.add("Ở nhà");
        listDiaDiem.add("Ở Bên Ngoài");


        List<String> listChoiCungAi=new ArrayList<>();
        listChoiCungAi.add("Một mình");
        listChoiCungAi.add("Cùng Bạn");
        listChoiCungAi.add("Cùng người yêu");



        List<String> listTamTrang=new ArrayList<>();
        listTamTrang.add("Buồn");
        listTamTrang.add("Vui");



        List<String> listThoiGian=new ArrayList<>();
        listThoiGian.add("Sáng");
        listThoiGian.add("Chiều");
        listThoiGian.add("Đêm");


        List<String> listNhatVat=new ArrayList<>();
        listNhatVat.add("Tank" );
        listNhatVat.add("Support" );
        listNhatVat.add("Carry" );





        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listDiaDiem);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnDiaDiem.setAdapter(adapter);
        spnDiaDiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  Toast.makeText(getActivity(), spnDiaDiem.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listChoiCungAi);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnChoiCungAi.setAdapter(adapter1);
        spnChoiCungAi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  Toast.makeText(getActivity(), spnChoiCungAi.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listTamTrang);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTamTrang.setAdapter(adapter2);
        spnTamTrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(), spnTamTrang.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listThoiGian);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnThoiGian.setAdapter(adapter3);
        spnThoiGian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(), spnThoiGian.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listNhatVat);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNhanVat.setAdapter(adapter4);
        spnNhanVat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(), spnNhanVat.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    class sentDataToServer extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer... params) {
            int nguCanh = params[0];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://musicismylife.atspace.cc/CARS.php");
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
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject cars=jsonArray.getJSONObject(i);

                    Ma_BAI_HAT = cars.getInt("maBanNhac");
                    USER = cars.getInt("user");
                    BAN_NHAC = cars.getInt("banNhac");
                    NGU_CANH_SERVER = cars.getInt("nguCanh");
                    RATE = cars.getInt("rate");
                    carsArrayList.add(new CARS(USER,BAN_NHAC,RATE));

                }




            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }


        }
    };



}
