package com.example.mypc.musicwithgame.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mypc.musicwithgame.Adpater.CustomAdapter;
import com.example.mypc.musicwithgame.Model.MyData;
import com.example.mypc.musicwithgame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MusicList extends AppCompatActivity {
    static int ID;
    static String TITLE,MP3,IMAGE;
    public RecyclerView recyclerView;
    public GridLayoutManager gridLayoutManager;
    public CustomAdapter adapter;
    public List<MyData> data_list;
   public ArrayList<HashMap<String, String>> mySongs_hm = new ArrayList<HashMap<String, String>>();
    /*String[] from = {"title", "title"};
    int[] to = {R.id.song_name, R.id.bmp};*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

       /* lv = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        String song_res = intent.getStringExtra("mp3");
*/
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, data_list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickedListener(new CustomAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(String description, int postion) {

                Intent i = new Intent(MusicList.this, Music_Player_Activity.class);

                //Truyền dữ liệu sang màn hình list nhạc
            /*    i.putExtra("id",ID);
                i.putExtra("tenBaiHat",TEN_BAI_HAT);
                i.putExtra("image",IMAGE);
                i.putExtra("mp3",MP3);
*/

                HashMap<String, String> selectedSong = mySongs_hm.get(postion);
                i.putExtra("sel_song", selectedSong);

                startActivity(i);
                Toast.makeText(MusicList.this, description, Toast.LENGTH_SHORT).show();
            }
           /* @Override
            public void onItemClick(String description) {


                Intent i = new Intent(MainActivity.this, MusicList.class);

                //Truyền dữ liệu sang màn hình list nhạc
                i.putExtra("id",ID);
                i.putExtra("tenBaiHat",TEN_BAI_HAT);
                i.putExtra("image",IMAGE);
                i.putExtra("mp3",MP3);


              *//*  HashMap<String, String> selectedSong = mySongs_hm.get(position);
                i.putExtra("sel_song", selectedSong);
*//*
                startActivity(i);
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
            }*/
        });
    }

    private void load_data_from_server(int id) {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                Intent intent = getIntent();
/*
                String song_res = intent.getStringExtra("mp3");
*/
                HashMap<String, String> sel_song = (HashMap<String, String>) intent.getSerializableExtra("sel_song");
                String song_res = sel_song.get("mp3");

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(song_res+"?id=" + integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        ID=object.getInt("id");
                        TITLE= object.getString("title");
                        IMAGE= object.getString("image");
                        MP3= object.getString("mp3");

                        HashMap hashMap=new HashMap();
                        hashMap.put("title",TITLE);
                        hashMap.put("image",IMAGE);
                        hashMap.put("mp3",MP3);

                        mySongs_hm.add(hashMap);


                        MyData data = new MyData(ID, TITLE,
                                IMAGE);

                        data_list.add(data);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {


                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }
   /* public void button_Add_Songs(View v)
    {
        Intent intent1 = new Intent(MusicList.this, Add_Songs.class);
        startActivity(intent1);
    }*/


}
