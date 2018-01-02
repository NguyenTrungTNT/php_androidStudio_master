package com.example.mypc.musicwithgame.Dialog;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.LoginActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MyPC on 28/12/2017.
 */

public class UpdateDialog extends DialogFragment {

 /*   HttpParse httpParse = new HttpParse();
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();*/
    static String name,hero,email,age,hobby,id;
    static String UPDATE_API="http://musicismylife.atspace.cc/update_user_info.php";
    static Button btUpdate,btCancel;
    static EditText etName,etEmail,etAge,etHero,etHobby;
    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static UpdateDialog newInstance(String data,String data1,String data2,String data3,String data4,String id) {
        UpdateDialog dialog = new UpdateDialog();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putSerializable("data1", data1);
        args.putSerializable("data2", data2);
        args.putSerializable("data3", data3);
        args.putSerializable("data4", data4);
        args.putSerializable("id", id);


       /* ArrayList<HashMap> hashMaps=(ArrayList<HashMap>) args.getSerializable("data");
        String email=hashMaps.ge*/
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lấy giá trị tự bundle
        final String data = getArguments().getString("data", "");
        String data1 = getArguments().getString("data1", "");
        String data2 = getArguments().getString("data2", "");
        String data3 = getArguments().getString("data3", "");
        String data4 = getArguments().getString("data4", "");
        id = getArguments().getString("id", "");


        btUpdate=(Button)view.findViewById(R.id.btnUpdate);
        btCancel=(Button)view.findViewById(R.id.btnCancel);
        etName=(EditText)view.findViewById(R.id.etName);
        etEmail=(EditText)view.findViewById(R.id.etEmail);
        etHobby=(EditText)view.findViewById(R.id.etHobby);
        etAge=(EditText)view.findViewById(R.id.etAge);
        etHero=(EditText)view.findViewById(R.id.etHero);



        etName.setText(data);
        etEmail.setText(data1);
        etHero.setText(data2);
        etHobby.setText(data3);
        etAge.setText(data4);


        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetDataFromEditText();

//                name=etName.getText().toString().trim();
//                email=etEmail.getText().toString().trim();
//                age=etAge.getText().toString().trim();
//                hero=etHero.getText().toString().trim();
//                hobby=etHobby.getText().toString().trim();
                if(name.equals("")||email.equals("")||hobby.equals("")||hero.equals("")||age.equals("")){
                  Toast.makeText(getActivity(),"Vui long nhap thong tin",Toast.LENGTH_LONG).show();
                }
                else {
                    UpdateData(UPDATE_API);
                  /*  UpdateData(id,name,email,hero,hobby,age);
                    startActivity(new Intent(getActivity(), AccountFragment.class));*/
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    public void GetDataFromEditText(){

        name=etName.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        age=etAge.getText().toString().trim();
        hero=etHero.getText().toString().trim();
        hobby=etHobby.getText().toString().trim();
    }
   /* public void UpdateData(final String ID, final String S_Name, final String S_email, final String S_hero,final String S_hobby,final String S_age){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);


                Toast.makeText(getActivity(),httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);

                hashMap.put("name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("hobby",params[3]);

                hashMap.put("hero",params[4]);

                hashMap.put("age",params[5]);


                finalResult = httpParse.postRequest(hashMap, UPDATE_API);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,S_Name,S_email,S_hero,S_hobby,S_age);
    }*/
    //Volley
    public void UpdateData(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(getActivity(),"Cap Nhat thanh cong",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getActivity(), LoginActivity.class));


                }else {
                    Toast.makeText(getActivity(),"Loi cap nhat",Toast.LENGTH_LONG).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Xay ra loi vui long thu lai",Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",id);
                params.put("name",etName.getText().toString().trim());
                params.put("email",etEmail.getText().toString().trim());
                params.put("age",etAge.getText().toString().trim());
                params.put("hero",etHero.getText().toString().trim());
                params.put("hobby",etHobby.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}

