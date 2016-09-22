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
    public static BeanMovieDao beanMovieDao;
    public static SQLiteDatabase db;
    public static int type_what = Constant.TYPE_POP;

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
        beanMovieDao = app.beanMovieDao;
        db = app.db;
        View view = inflater.inflate(R.layout.fragment_pop_movie, container, false);
        rv_movie_list = (RecyclerView) view.findViewById(R.id.rv_movie_list);
        initData(type_what);
//        getLoaderManager().initLoader(Constant.URL_LOADER,null,this);
        return view;
    }

    private void initData(int type) {

        MyIntentService.startActionPop(sContext, ApiEntity.PopURL+ Constant.PAGE);
        MyIntentService.startActionRated(sContext, ApiEntity.RatedURL+ Constant.PAGE);
        app = (MyApplication) getActivity().getApplication();
        changeOrder(type);

    }

    private void setView(ArrayList<BeanMovie> movies) {
        adapter = new MovieListAdapter(getActivity(), (ArrayList<BeanMovie>) movies);
        rv_movie_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rv_movie_list.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }
    public static class MyCursorLoader extends CursorLoader {

        public MyCursorLoader(Context context) {
            super(context);
        }

        public Cursor loadInBackground(){
            return db.query(beanMovieDao.getTablename(),beanMovieDao.getAllColumns(), null, null, null, null, null);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case Constant.URL_LOADER:
                return new MyCursorLoader(sContext);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!=null){
            while (data.moveToNext()){
                if (data.getInt(data.getColumnIndex(BeanMovieDao.Properties.Movie_type.columnName))==type_what||
                        data.getInt(data.getColumnIndex(BeanMovieDao.Properties.Favorite.columnName))==type_what) {
                    String title = data.getString(data.getColumnIndex(BeanMovieDao.Properties.Title.columnName));
                    Log.i("tag", "onLoadFinished: " + title);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void changeOrder(int type) {
        ArrayList<BeanMovie> movies;
        QueryBuilder qb =  app.daoSession.getBeanMovieDao().queryBuilder();
        if (type==Constant.TYPE_FAVORITE){
            qb.where(BeanMovieDao.Properties.Favorite.eq(Constant.FAVORITE_YES));
        }else {
            qb.where(BeanMovieDao.Properties.Movie_type.eq(type));
        }
        movies = (ArrayList<BeanMovie>) qb.list();
        setView(movies);
        type_what = type;
        getLoaderManager().initLoader(Constant.URL_LOADER,null,this);
    }

}
