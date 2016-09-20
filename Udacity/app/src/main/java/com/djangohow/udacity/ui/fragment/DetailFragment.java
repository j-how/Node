package com.djangohow.udacity.ui.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.djangohow.udacity.MyApplication;
import com.djangohow.udacity.R;
import com.djangohow.udacity.adapter.ReviewListAdapter;
import com.djangohow.udacity.adapter.VideoListAdapter;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.selfDefine.ExpandListView;
import com.djangohow.udacity.service.MyIntentService;
import com.djangohow.udacity.vo.MovieBean;
import com.djangohow.udacity.vo.ReviewList;
import com.djangohow.udacity.vo.VideoList;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.udacity.bean.BeanMovie;
import com.udacity.dao.BeanMovieDao;

import org.greenrobot.greendao.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener,MyIntentService.VideoListListener,MyIntentService.ReviewListListener{
    private TextView original_title,release_date,original_language,vote_average,overview,tv_hint;
    private ExpandListView layout_video,elv_review;
    private ImageView poster_path;
    private SimpleDraweeView sdw_detail;
    private Button bt_add_to_favorite;
    private MyApplication app;
    private BeanMovie mBeanMovie;
    private LayoutInflater mLayoutInflater;
    private View view_up_video,view_up_review;
    private static Context sContext;
    private final int VIDEO_LIST=1;
    private final int REVIEW_LIST=2;
    public static BeanMovie sMovie;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case VIDEO_LIST:
                    Bundle data_video = msg.getData();
                    ArrayList<VideoList.ResultsBean> beans_video = data_video.getParcelableArrayList("videos");
                    if (beans_video!=null) {
                        VideoListAdapter adapter = new VideoListAdapter(sContext, beans_video);
                        layout_video.setAdapter(adapter);
                        view_up_video.setVisibility(View.VISIBLE);
                    }
                    break;
                case REVIEW_LIST:
                    Bundle data_review = msg.getData();
                    ArrayList<ReviewList.ResultsBean> beans_review = data_review.getParcelableArrayList("reviews");
                    if (beans_review!=null) {
                        ReviewListAdapter adapter = new ReviewListAdapter(sContext, beans_review);
                        elv_review.setAdapter(adapter);
                        view_up_review.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }
    };
    public static DetailFragment newInstance(Context context) {
        Bundle args = new Bundle();
        sContext = context;
        args.putParcelable("movie",sMovie);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayoutInflater = LayoutInflater.from(sContext);
        app = (MyApplication) getActivity().getApplication();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initView(view);
       if (sMovie!=null){
           setValue(sMovie.getPoster_path(),sMovie);
       }
        return view;
    }

    private void initView(View view) {
        original_title = (TextView) view.findViewById(R.id.original_title);
        release_date = (TextView) view.findViewById(R.id.release_date);
        original_language = (TextView) view.findViewById(R.id.original_language);
        vote_average = (TextView) view.findViewById(R.id.vote_average);
        overview = (TextView) view.findViewById(R.id.overview);
        tv_hint = (TextView) view.findViewById(R.id.tv_hint);
        layout_video = (ExpandListView) view.findViewById(R.id.layout_video);
        elv_review = (ExpandListView) view.findViewById(R.id.elv_review);
        view_up_video = view.findViewById(R.id.view_up_video);
        view_up_review = view.findViewById(R.id.view_up_review);
        bt_add_to_favorite = (Button) view.findViewById(R.id.bt_add_to_favorite);
        bt_add_to_favorite.setOnClickListener(this);

        poster_path = (ImageView) view.findViewById(R.id.poster_path);

//        sdw_detail = (SimpleDraweeView) view.findViewById(R.id.sdw_detail);

    }

    public void setValue(String url, BeanMovie bean) {
        //保存状态
        sMovie = bean;


        original_title.setText(bean.getOriginal_title());
        release_date.setText(bean.getRelease_date());
        original_language.setText(bean.getOriginal_language());
        vote_average.setText(bean.getVote_average()+"/10");
        overview.setText(bean.getOverview());
        Picasso.with(getContext()).load(url).into(poster_path);
//        Uri uri = Uri.parse(url);
        //android:background="@color/backgroud"
        int color = sContext.getResources().getColor(R.color.backgroud);
        original_title.setBackgroundColor(color);
//        android:background="@drawable/bt_favorite"
        Drawable button_bg = sContext.getResources().getDrawable(R.drawable.bt_favorite);
        bt_add_to_favorite.setBackground(button_bg);
        tv_hint.setVisibility(View.GONE);

        if (bean.getFavorite()){
            bt_add_to_favorite.setSelected(true);
        }
        mBeanMovie = bean;
//        sdw_detail.setImageURI(uri);
        MyIntentService.startActionVideoList(sContext, ApiEntity.BaseURL+bean.getMovie_id()+ApiEntity.VideoURL,this);
        MyIntentService.startActionReviewList(sContext,ApiEntity.BaseURL+bean.getMovie_id()+ApiEntity.ReviewsURL,this);
    }

     public void setVideoList(ArrayList<VideoList.ResultsBean> beans) {

         Message message = mHandler.obtainMessage();
         Bundle data = new Bundle();
         data.putParcelableArrayList("videos", beans);
         message.setData(data);
         message.what = VIDEO_LIST;
         mHandler.sendMessage(message);
    }

    @Override
    public void setReviewList(ArrayList<ReviewList.ResultsBean> beans) {
        Message message = mHandler.obtainMessage();
        Bundle data = new Bundle();
        data.putParcelableArrayList("reviews", beans);
        message.setData(data);
        message.what = REVIEW_LIST;
        mHandler.sendMessage(message);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_to_favorite:
                if (bt_add_to_favorite.isSelected()){
                    bt_add_to_favorite.setSelected(false);
                    String favorite_delete = sContext.getResources().getString(R.string.favarite_delete);
                    Toast.makeText(sContext, favorite_delete, Toast.LENGTH_SHORT).show();
                    mBeanMovie.setFavorite(Constant.FAVORITE_NO);
                    app.daoSession.getBeanMovieDao().update(mBeanMovie);
                }else {
                    bt_add_to_favorite.setSelected(true);
                    String favorite_add = sContext.getResources().getString(R.string.favarite_add);
                    Toast.makeText(sContext, favorite_add, Toast.LENGTH_SHORT).show();
                    mBeanMovie.setFavorite(Constant.FAVORITE_YES);
                    app.daoSession.getBeanMovieDao().update(mBeanMovie);
                }
                break;
        }
    }

}
