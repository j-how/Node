package com.djangohow.udacity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.djangohow.udacity.R;
import com.djangohow.udacity.vo.ReviewList;

import java.util.ArrayList;

/**
 * Created by django on 2016/9/19.
 */
public class ReviewListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<ReviewList.ResultsBean> mList;

    public ReviewListAdapter(Context context, ArrayList<ReviewList.ResultsBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        MyListener myListener;
        if (convertView==null){
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.review_item,parent,false);
            holder.review_item_tv_content = (TextView) convertView.findViewById(R.id.review_item_tv_content);
            holder.review_item_tv_author = (TextView) convertView.findViewById(R.id.review_item_tv_author);
            holder.review_item_iv = (ImageView) convertView.findViewById(R.id.review_item_iv);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        myListener = new MyListener(position,holder);
        holder.review_item_iv.setOnClickListener(myListener);

        final int maxDescripLine = 3; //TextView默认最大展示行数

        holder.review_item_tv_content.setText(mList.get(position).getContent());
        holder.review_item_tv_author.setText(mList.get(position).getAuthor());
        holder.review_item_tv_content.setHeight(holder.review_item_tv_content.getLineHeight()*maxDescripLine);

        return convertView;
    }
    class MyViewHolder{
        TextView review_item_tv_content;
        TextView review_item_tv_author;
        ImageView review_item_iv;
    }
    class MyListener implements View.OnClickListener{
        int position;
        MyViewHolder holder;
        public MyListener(int position, MyViewHolder holder) {
            this.position = position;
            this.holder = holder;
        }
        boolean isExpand = true;//是否已展开的状态
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.review_item_iv:

                    isExpand = !isExpand;
                    if (isExpand){
                        holder.review_item_tv_content.setHeight(holder.review_item_tv_content.getLineHeight() * 3);
                    }else {
                        int defaultHight = holder.review_item_tv_content.getHeight();
                        holder.review_item_tv_content.setHeight(holder.review_item_tv_content.getLineHeight() * 8);
//                    holder.review_item_tv_content.setVisibility(View.GONE);
                        Toast.makeText(mContext, "xxx:" + position, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
