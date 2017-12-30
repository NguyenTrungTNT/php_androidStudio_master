package com.example.mypc.musicwithgame.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.mypc.musicwithgame.Adpater.CustomAdapter;
import com.example.mypc.musicwithgame.Fragment.AccountFragment;
import com.example.mypc.musicwithgame.Fragment.FavoritesFragment;
import com.example.mypc.musicwithgame.Fragment.HomeFragment;
import com.example.mypc.musicwithgame.Model.MyData;
import com.example.mypc.musicwithgame.R;
import com.mikepenz.materialdrawer.Drawer;
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

public class MainActivity extends AppCompatActivity {


/*
    public RecyclerView recyclerView;
    public GridLayoutManager gridLayoutManager;
    public CustomAdapter adapter;
    public List<MyData> data_list;
    static String TEN_BAI_HAT,MP3,IMAGE;
    static int ID;
    public ArrayList<HashMap<String, String>> mySongs_hm = new ArrayList<HashMap<String, String>>();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = FavoritesFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = AccountFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

/*
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, data_list);
        recyclerView.setAdapter(adapter);


        //Event

        adapter.setOnItemClickedListener(new CustomAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(String description, int postion) {

                Intent i = new Intent(MainActivity.this, MusicList.class);

                //Truyền dữ liệu sang màn hình list nhạc
            */
/*    i.putExtra("id",ID);
                i.putExtra("tenBaiHat",TEN_BAI_HAT);
                i.putExtra("image",IMAGE);
                i.putExtra("mp3",MP3);
*//*


                HashMap<String, String> selectedSong = mySongs_hm.get(postion);
                i.putExtra("sel_song", selectedSong);

                startActivity(i);
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
            }
           */
/* @Override
            public void onItemClick(String description) {


                Intent i = new Intent(MainActivity.this, MusicList.class);

                //Truyền dữ liệu sang màn hình list nhạc
                i.putExtra("id",ID);
                i.putExtra("tenBaiHat",TEN_BAI_HAT);
                i.putExtra("image",IMAGE);
                i.putExtra("mp3",MP3);


              *//*
*/
/*  HashMap<String, String> selectedSong = mySongs_hm.get(position);
                i.putExtra("sel_song", selectedSong);
*//*
*/
/*
                startActivity(i);
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
            }*//*

        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size() - 1) {
                    load_data_from_server(data_list.get(data_list.size() - 1).getId());
                }

            }
        });
*/


    }

  /*  private void load_data_from_server(int id) {

        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://musicismylife.atspace.cc/MusicPlayerJson.php?id=" + integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);


                        ID=object.getInt("id");
                        TEN_BAI_HAT= object.getString("tenBaiHat");
                        IMAGE= object.getString("image");
                        MP3= object.getString("mp3");

                        HashMap<String, String> map = new HashMap<String, String>();

                        map.clear();

                     *//*   map.put("song_id", ID);*//*

                        map.put("song_name", TEN_BAI_HAT);
                        map.put("mp3", MP3);
                        map.put("image",IMAGE);

                        mySongs_hm.add(map);
                        MyData data = new MyData(ID, TEN_BAI_HAT,
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
    }*/



//}