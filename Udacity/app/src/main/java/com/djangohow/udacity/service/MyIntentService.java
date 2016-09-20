package com.djangohow.udacity.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.djangohow.udacity.MyApplication;
import com.djangohow.udacity.R;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.utils.NetUtils;
import com.djangohow.udacity.vo.JsonResult;
import com.djangohow.udacity.vo.MovieBean;
import com.djangohow.udacity.vo.ReviewList;
import com.djangohow.udacity.vo.VideoList;
import com.google.gson.Gson;
import com.udacity.bean.BeanMovie;
import com.udacity.dao.BeanMovieDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService{
    public static MyApplication app;
    public static VideoListListener mVideoListListener;
    public static ReviewListListener sReviewListListener;
    public interface VideoListListener{
        void setVideoList(ArrayList<VideoList.ResultsBean> beans);
    }
    public interface ReviewListListener{
        void setReviewList(ArrayList<ReviewList.ResultsBean> beans);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = (MyApplication) getApplication();
    }

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_Rated = "com.djangohow.udacity.service.action.RATED";
    private static final String ACTION_Pop = "com.djangohow.udacity.service.action.POP";
    private static final String ACTION_Video_List = "com.djangohow.udacity.service.action.VIDEO.LIST";
    private static final String ACTION_Review_List = "com.djangohow.udacity.service.action.REVIEW.LIST";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.djangohow.udacity.service.extra.PARAM1";
//    private static final String EXTRA_PARAM2 = "com.djangohow.udacity.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionRated(Context context, String url) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_Rated);
        intent.putExtra(EXTRA_PARAM1, url);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionPop(Context context, String url) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_Pop);
        intent.putExtra(EXTRA_PARAM1, url);
        context.startService(intent);
    }
    public static void startActionVideoList(Context context, String url,VideoListListener videoListListener) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_Video_List);
        intent.putExtra(EXTRA_PARAM1, url);
        mVideoListListener = videoListListener;
//        intent.putExtra(EXTRA_PARAM2, (Serializable) videoListListener);
        context.startService(intent);
    }
    public static void startActionReviewList(Context context, String url,ReviewListListener reviewListListener) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_Review_List);
        intent.putExtra(EXTRA_PARAM1, url);
        sReviewListListener = reviewListListener;
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_Rated.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionRated(param1);
            } else if (ACTION_Pop.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionPop(param1);
            }else if (ACTION_Video_List.equals(action)){
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionVideoList(param1);
            }else if (ACTION_Review_List.equals(action)){
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionReviewList(param1);
            }
        }
    }

    private void handleActionReviewList(String param1) {

        String json = NetUtils.getString(param1);
        Gson gson = new Gson();
        ReviewList reviewList = gson.fromJson(json,ReviewList.class);
        sReviewListListener.setReviewList((ArrayList<ReviewList.ResultsBean>) reviewList.getResults());
    }

    private void handleActionVideoList(String param1) {

        String json = NetUtils.getString(param1);
        Gson gson = new Gson();
        VideoList videoList = gson.fromJson(json,VideoList.class);
        mVideoListListener.setVideoList((ArrayList<VideoList.ResultsBean>) videoList.getResults());
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionRated(String param1) {
        // TODO: Handle action Foo
        String json = NetUtils.getString(param1);
        List<MovieBean> movies = getMovies(json);

        if (movies!=null){
            for (MovieBean movie:movies){
                //判断数据库中是否已经存在此movie
                Query qb = app.daoSession.getBeanMovieDao().queryBuilder().
                        where(BeanMovieDao.Properties.Movie_id.eq(movie.id)).build();
                ArrayList<BeanMovie> beanMovies = (ArrayList<BeanMovie>) qb.list();
//                Log.i("tag", "beanMovies.size()+rated:"+beanMovies.size());
                if (beanMovies.size()!=0)continue;
                BeanMovie ratedMovie = new BeanMovie();
                ratedMovie.setPoster_path(movie.poster_path);
                ratedMovie.setAdult(movie.adult);
                ratedMovie.setOverview(movie.overview);
                ratedMovie.setRelease_date(movie.release_date);
                ratedMovie.setMovie_id(movie.id);
                ratedMovie.setOriginal_title(movie.original_title);
                ratedMovie.setOriginal_language(movie.original_language);
                ratedMovie.setTitle(movie.title);
                ratedMovie.setBackdrop_path(movie.backdrop_path);
                ratedMovie.setPopularity(movie.popularity);
                ratedMovie.setVote_count(movie.vote_count);
                ratedMovie.setVideo(movie.video);
                ratedMovie.setVote_average(movie.vote_average);
                ratedMovie.setMovie_type(Constant.TYPE_RATED);
                ratedMovie.setFavorite(Constant.FAVORITE_NO);
                StringBuilder genre_ids = new StringBuilder();
                for (int i=0;i<movie.genre_ids.length;i++){
                    genre_ids.append(movie.genre_ids[i]).append(",");
                }
                ratedMovie.setGenre_ids(genre_ids.toString());
                app.daoSession.getBeanMovieDao().insert(ratedMovie);
            }

        }else {
            String hint = this.getResources().getString(R.string.hint_net_unstable);
            Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionPop(String param1) {
        // TODO: Handle action Baz
        String json = NetUtils.getString(param1);
        List<MovieBean> movies = getMovies(json);
        if (movies!=null){
            for (MovieBean movie:movies){
//                Log.i("tag", "title: "+ movie.title +"  id: "+movie.id);
                //判断数据库中是否已经存在此movie
                Query qb = app.daoSession.getBeanMovieDao().queryBuilder().
                        where(BeanMovieDao.Properties.Movie_id.eq(movie.id)).build();
                ArrayList<BeanMovie> beanMovies = (ArrayList<BeanMovie>) qb.list();
//                Log.i("tag", "beanMovies.size()+pop:"+beanMovies.size());
                if (beanMovies.size()!=0)continue;
                BeanMovie popMovie = new BeanMovie();
                popMovie.setPoster_path(movie.poster_path);
                popMovie.setAdult(movie.adult);
                popMovie.setOverview(movie.overview);
                popMovie.setRelease_date(movie.release_date);
                popMovie.setMovie_id(movie.id);
                popMovie.setOriginal_title(movie.original_title);
                popMovie.setOriginal_language(movie.original_language);
                popMovie.setTitle(movie.title);
                popMovie.setBackdrop_path(movie.backdrop_path);
                popMovie.setPopularity(movie.popularity);
                popMovie.setVote_count(movie.vote_count);
                popMovie.setVideo(movie.video);
                popMovie.setVote_average(movie.vote_average);
                popMovie.setMovie_type(Constant.TYPE_POP);
                popMovie.setFavorite(Constant.FAVORITE_NO);
                StringBuilder genre_ids = new StringBuilder();
                for (int i=0;i<movie.genre_ids.length;i++){
                    genre_ids.append(movie.genre_ids[i]).append(",");
                }
                popMovie.setGenre_ids(genre_ids.toString());
                app.daoSession.getBeanMovieDao().insert(popMovie);
//                Log.i("tag", "插入数据库 ");
            }

        }else {
            String hint = this.getResources().getString(R.string.hint_net_unstable);
            Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
        }
    }


    private List<MovieBean> getMovies(String json) {
        ArrayList<MovieBean> movies = null;
        Gson gson = new Gson();
        JsonResult movie = gson.fromJson(json, JsonResult.class);
        if (movie == null) {
            return movies;
        } else {
            Log.i("tag", "movie:" + movie);
            movies = (ArrayList<MovieBean>) movie.results;
            return movies;
        }
    }
}
