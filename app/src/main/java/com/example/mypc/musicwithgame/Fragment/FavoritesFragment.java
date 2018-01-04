package com.example.mypc.musicwithgame.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mypc.musicwithgame.Model.CARS;
import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.Rate_Activity;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {


    ListView listview;

    static String nhanVat,tamTrang,thoiGian;
    static int Ma_BAI_HAT,BAN_NHAC,USER,RATE,NGU_CANH_SERVER;
    static int NGU_CANH;
    static String CARS_res="http://musicismylife.atspace.cc/demoCars.php";
    static Spinner spnDiaDiem,spnTamTrang,spnChoiCungAi,spnThoiGian,spnNhanVat;
    static ArrayList<CARS> carsArrayList;
    static Button submit;
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


   /*     sentDataToServer sent=new sentDataToServer();
        String nc=String.valueOf(NGU_CANH);
        sent.execute(nc);*/

    /*    listview = (ListView)v.findViewById(R.id.lv);

        listview.setAdapter(new Adapter(getActivity(), carsArrayList));*/


        //HashMap<String, Integer> user=listMap.get(1);




        Spiner_Manager();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();
                int nc=truongHopNguCanh();
                String ngu_canh=String.valueOf(nc);
                Intent intent=new Intent(getActivity(),Rate_Activity.class);
                intent.putExtra("nguCanh",ngu_canh);
                startActivity(intent);


                Toast.makeText(getActivity(),ngu_canh+"user:"+USER+"Bản nhạc:"+BAN_NHAC+"rate:"+RATE,Toast.LENGTH_LONG).show();



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









}
