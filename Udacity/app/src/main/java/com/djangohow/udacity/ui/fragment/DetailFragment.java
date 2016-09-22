package com.djangohow.udacity.ui.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.djangohow.udacity.vo.ReviewList;
import com.djangohow.udacity.vo.VideoList;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.udacity.bean.BeanMovie;

import java.util.ArrayList;

;import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener, MyIntentService.VideoListListener, MyIntentService.ReviewListListener {
    @BindView(R.id.original_title)
    TextView mOriginalTitle;
    @BindView(R.id.poster_path)
    ImageView mPosterPath;
    @BindView(R.id.release_date)
    TextView mReleaseDate;
    @BindView(R.id.original_language)
    TextView mOriginalLanguage;
    @BindView(R.id.vote_average)
    TextView mVoteAverage;
    @BindView(R.id.bt_add_to_favorite)
    Button mBtAddToFavorite;
    @BindView(R.id.overview)
    TextView mOverview;
    @BindView(R.id.view_up_video)
    View mViewUpVideo;
    @BindView(R.id.lv_video)
    ExpandListView mLvVideo;
    @BindView(R.id.view_up_review)
    View mViewUpReview;
    @BindView(R.id.elv_review)
    ExpandListView mElvReview;
    @BindView(R.id.ll_detail)
    LinearLayout mLlDetail;
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    private MyApplication app;
    private BeanMovie mBeanMovie;
    private LayoutInflater mLayoutInflater;
    private static Context sContext;
    private final int VIDEO_LIST = 1;
    private final int REVIEW_LIST = 2;
    public static BeanMovie sMovie;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case VIDEO_LIST:
                    Bundle data_video = msg.getData();
                    ArrayList<VideoList.ResultsBean> beans_video = data_video.getParcelableArrayList("videos");
                    if (beans_video != null) {
                        VideoListAdapter adapter = new VideoListAdapter(sContext, beans_video);
                        mLvVideo.setAdapter(adapter);
                        mViewUpVideo.setVisibility(View.VISIBLE);
                    }
                    break;
                case REVIEW_LIST:
                    Bundle data_review = msg.getData();
                    ArrayList<ReviewList.ResultsBean> beans_review = data_review.getParcelableArrayList("reviews");
                    if (beans_review != null) {
                        ReviewListAdapter adapter = new ReviewListAdapter(sContext, beans_review);
                        mElvReview.setAdapter(adapter);
                        mViewUpReview.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }
    };

    public static DetailFragment newInstance(Context context) {
        Bundle args = new Bundle();
        sContext = context;
        args.putParcelable("movie", sMovie);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutInflater = LayoutInflater.from(sContext);
        app = (MyApplication) getActivity().getApplication();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if (sMovie != null) {
            setValue(sMovie.getPoster_path(), sMovie);
        }
        mBtAddToFavorite.setOnClickListener(this);
        return view;
    }

    public void setValue(String url, BeanMovie bean) {
        //保存状态
        sMovie = bean;
        mOriginalTitle.setText(bean.getOriginal_title());
        mReleaseDate.setText(bean.getRelease_date());
        mOriginalLanguage.setText(bean.getOriginal_language());
        mVoteAverage.setText(bean.getVote_average() + "/10");
        mOverview.setText(bean.getOverview());
        Picasso.with(getContext()).load(url).into(mPosterPath);
        int color = sContext.getResources().getColor(R.color.backgroud);
        mOriginalTitle.setBackgroundColor(color);
        Drawable button_bg = sContext.getResources().getDrawable(R.drawable.bt_favorite);
        mBtAddToFavorite.setBackground(button_bg);
        mTvHint.setVisibility(View.GONE);
        if (bean.getFavorite()) {
            mBtAddToFavorite.setSelected(true);
        }
        mBeanMovie = bean;
        MyIntentService.startActionVideoList(sContext, ApiEntity.BaseURL + bean.getMovie_id() + ApiEntity.VideoURL, this);
        MyIntentService.startActionReviewList(sContext, ApiEntity.BaseURL + bean.getMovie_id() + ApiEntity.ReviewsURL, this);
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
        switch (v.getId()) {
            case R.id.bt_add_to_favorite:
                if (mBtAddToFavorite.isSelected()) {
                    mBtAddToFavorite.setSelected(false);
                    String favorite_delete = sContext.getResources().getString(R.string.favarite_delete);
                    Toast.makeText(sContext, favorite_delete, Toast.LENGTH_SHORT).show();
                    mBeanMovie.setFavorite(Constant.FAVORITE_NO);
                    app.daoSession.getBeanMovieDao().update(mBeanMovie);
                } else {
                    mBtAddToFavorite.setSelected(true);
                    String favorite_add = sContext.getResources().getString(R.string.favarite_add);
                    Toast.makeText(sContext, favorite_add, Toast.LENGTH_SHORT).show();
                    mBeanMovie.setFavorite(Constant.FAVORITE_YES);
                    app.daoSession.getBeanMovieDao().update(mBeanMovie);
                }
                break;
        }
    }

}
