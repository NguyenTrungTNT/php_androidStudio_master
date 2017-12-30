package com.example.mypc.musicwithgame.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mypc.musicwithgame.Dialog.UpdateDialog;
import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class AccountFragment extends Fragment {
    private  AccountFragment myContext;
    static ArrayList<HashMap<String,String>> hashMaps;
    static TextView tvName,tvHobby,tvEmail,tvHero,tvAge;
    static String name,age,hobby,email,hero,id;
    static Button imageButton,btnEdit;
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_account,container,false);

        tvName=(TextView)v.findViewById(R.id.tvName);
        tvEmail=(TextView)v.findViewById(R.id.tvEmail);
        tvHero=(TextView)v.findViewById(R.id.tvHero);
        tvHobby=(TextView)v.findViewById(R.id.tvHobby);
        tvAge=(TextView)v.findViewById(R.id.tvAge);
        imageButton=(Button) v.findViewById(R.id.btnOut);
        btnEdit=(Button)v.findViewById(R.id.btnEdit);


        Intent intent= getActivity().getIntent();

        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        hero=intent.getStringExtra("hero");
        hobby=intent.getStringExtra("hobby");
        age=intent.getStringExtra("age");
        id=intent.getStringExtra("id");

        hashMaps=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("email",email);
        map.put("hero",hero);
        map.put("hobby",hobby);
        map.put("age",age);
        hashMaps.add(map);

        tvName.setText(name);
        tvEmail.setText(email);
        tvHero.setText(hero);
        tvAge.setText(age);
        tvHobby.setText(hobby);



        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                Intent i=new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                UpdateDialog updateDialog=UpdateDialog.newInstance(name,email,hero,hobby,age,id);
                updateDialog.show(fm,null);
            }
        });

        return v;
    }


}
