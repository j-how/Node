package com.djangohow.udacity.ui.fragment;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djangohow.udacity.MyApplication;
import com.djangohow.udacity.R;
import com.djangohow.udacity.adapter.MovieListAdapter;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.service.MyIntentService;
import com.udacity.bean.BeanMovie;
import com.udacity.dao.BeanMovieDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopMovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private RecyclerView rv_movie_list;
    public MovieListAdapter adapter;
    public static Context sContext;
    private MyApplication app;

    public static int type_what = Constant.TYPE_POP;
    private ArrayList<BeanMovie> mMovies;
    public static PopMovieFragment newInstance(Context context) {
        Bundle args = new Bundle();
//        args.putInt("type",type_what);
        PopMovieFragment fragment = new PopMovieFragment();
        sContext = context;
        fragment.setArguments(args);
        return fragment;
    }
    public PopMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pop_movie, container, false);
        rv_movie_list = (RecyclerView) view.findViewById(R.id.rv_movie_list);
//        initData(type_what);
        getLoaderManager().initLoader(Constant.URL_LOADER,null,this);
        mMovies = new ArrayList<>();
        return view;
    }

    private void initData(int type) {

        MyIntentService.startActionPop(sContext, ApiEntity.PopURL+ Constant.PAGE);
        MyIntentService.startActionRated(sContext, ApiEntity.RatedURL+ Constant.PAGE);
        app = (MyApplication) getActivity().getApplication();
//        changeOrder(type);

    }

    private void setView(ArrayList<BeanMovie> movies) {
        adapter = new MovieListAdapter(getActivity(), (ArrayList<BeanMovie>) movies);
        rv_movie_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rv_movie_list.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constant.URL_LOADER:
                return new MyIntentService.MyCursorLoader(sContext);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!=null){
            while (data.moveToNext()){

                Long id = data.getLong(data.getColumnIndex(BeanMovieDao.Properties.Id.columnName));
                String poster_path = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Poster_path.columnName));
                Boolean adult = Boolean.valueOf(data.getString(data.getColumnIndex(BeanMovieDao.Properties.Poster_path.columnName)));
                String overview = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Overview.columnName));
                String release_date = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Release_date.columnName));
                String genre_ids = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Genre_ids.columnName));
                Integer movie_id = data.getInt(data.getColumnIndex(BeanMovieDao.Properties.Movie_id.columnName));
                String original_title = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Original_title.columnName));
                String original_language = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Original_language.columnName));
                String title = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Title.columnName));
                String backdrop_path = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Backdrop_path.columnName));
                Float popularity = data.getFloat(data.getColumnIndex(BeanMovieDao.Properties.Popularity.columnName));
                Integer vote_count = data.getInt(data.getColumnIndex(BeanMovieDao.Properties.Vote_count.columnName));
                Boolean video = Boolean.valueOf(data.getString(data.getColumnIndex(BeanMovieDao.Properties.Video.columnName)));
                Float vote_average = data.getFloat(data.getColumnIndex(BeanMovieDao.Properties.Vote_average.columnName));
                Integer movie_type = data.getInt(data.getColumnIndex(BeanMovieDao.Properties.Movie_type.columnName));
                Boolean favorite = Boolean.valueOf(data.getString(data.getColumnIndex(BeanMovieDao.Properties.Favorite.columnName)));

                BeanMovie movie = new BeanMovie(id,poster_path,adult,overview,release_date,genre_ids,movie_id,original_title,original_language,
                        title,backdrop_path,popularity,vote_count,video,vote_average,movie_type,favorite);
                mMovies.add(movie);
                changeOrder(type_what);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void changeOrder(int type) {
        ArrayList<BeanMovie> movies = new ArrayList<>();
        if (type==Constant.TYPE_FAVORITE){
            for (BeanMovie movie:mMovies){
                if (movie.getFavorite()==true){
                    movies.add(movie);
                }
            }
        }else {
            for (BeanMovie movie:mMovies){
                if (movie.getMovie_type()==type){
                    movies.add(movie);
                }
            }
        }
        setView(movies);
        type_what = type;
    }

}
