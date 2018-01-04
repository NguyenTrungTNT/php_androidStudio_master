package com.example.mypc.musicwithgame.Adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.musicwithgame.Model.MyData;
import com.example.mypc.musicwithgame.R;
import com.example.mypc.musicwithgame.View.MusicList;

import java.util.List;

/**
 * Created by MyPC on 05/12/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.description.setText(my_data.get(position).getDescription());
        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);

        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Intent intent= new Intent(this, MusicList.class);
*/

              /*  holder.line.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));*/
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(holder.description.getText().toString(),position);
/*
                    onItemClickedListener.onItemClick2(holder.imageView);
*/
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView description;
        public ImageView imageView;
        public LinearLayout line;
        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            line=(LinearLayout)itemView.findViewById(R.id.line);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(String description,int postion);
/*
        void  onItemClick2(String imageView);
*/
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }


}
