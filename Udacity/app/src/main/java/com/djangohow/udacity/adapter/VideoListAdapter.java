package com.djangohow.udacity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.djangohow.udacity.R;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.vo.VideoList;

import java.util.ArrayList;

/**
 * Created by django on 2016/9/19.
 */
public class VideoListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<VideoList.ResultsBean> mList;

    public VideoListAdapter(Context context, ArrayList<VideoList.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
            holder.video_item_tv = (TextView) convertView.findViewById(R.id.video_item_tv);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.video_item_tv.setText(mList.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApiEntity.YouTubeURL+mList.get(position).getKey()));
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }

            }
        });
        return convertView;
    }

    class MyViewHolder{
        TextView video_item_tv;
    }
}
