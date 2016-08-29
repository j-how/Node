package com.djangohow.udacity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.djangohow.udacity.R;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.utils.MessageEvent;
import com.djangohow.udacity.vo.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by django on 2016/8/28.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<MovieBean> mList;

    public MovieListAdapter(Context context, ArrayList<MovieBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Uri uri = Uri.parse(ApiEntity.ImageURL+mList.get(position).poster_path);
//        Log.i("tag",uri.toString());
//        Log.i("tag",holder.toString());
        Picasso.with(mContext).load(uri).into(holder.iv_item_moive);
//        holder.sdw_item_moive.setImageURI(uri);
        holder.iv_item_moive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(uri.toString(),mList.get(position)));
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView sdw_item_moive;
        public ImageView iv_item_moive;
        public MyViewHolder(View itemView) {
            super(itemView);
            sdw_item_moive = (SimpleDraweeView) itemView.findViewById(R.id.sdw_item_moive);
            iv_item_moive = (ImageView) itemView.findViewById(R.id.iv_item_moive);
        }
    }
}
