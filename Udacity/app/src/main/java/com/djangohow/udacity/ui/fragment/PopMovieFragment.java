package com.djangohow.udacity.ui.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.djangohow.udacity.R;
import com.djangohow.udacity.adapter.MovieListAdapter;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.utils.HandleUrl;
import com.djangohow.udacity.vo.JsonResult;
import com.djangohow.udacity.vo.MovieBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopMovieFragment extends Fragment {
    private RecyclerView rv_movie_list;
    private String page = "1";
    public MovieListAdapter adapter;
    public static PopMovieFragment newInstance() {

        Bundle args = new Bundle();

        PopMovieFragment fragment = new PopMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public PopMovieFragment() {
        // Required empty public constructor
//        Toast.makeText(getActivity(), "PopMovieFragment", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pop_movie, container, false);
        rv_movie_list = (RecyclerView) view.findViewById(R.id.rv_movie_list);
        changeOrder(ApiEntity.PopURL+page);
        return view;
    }

    public void changeOrder(String url) {
        new MyAsyncTask().execute(url);
    }

    private class MyAsyncTask extends AsyncTask<String, Void, List<MovieBean>>{

        @Override
        protected List<MovieBean> doInBackground(String... params) {
            String url = params[0];
            String json = HandleUrl.getString(url);
            List<MovieBean> movies = getMovies(json);
            return movies;
        }

        @Override
        protected void onPostExecute(List<MovieBean> movieBean) {
            super.onPostExecute(movieBean);
            Log.i("tag",movieBean.get(0).toString());
            adapter = new MovieListAdapter(getActivity(), (ArrayList<MovieBean>) movieBean);
            rv_movie_list.setAdapter(adapter);
            rv_movie_list.setLayoutManager(new GridLayoutManager(getContext(),4));
        }
    }
    private List<MovieBean> getMovies(String json) {
        Gson gson = new Gson();
        JsonResult movie = gson.fromJson(json,JsonResult.class);
        ArrayList<MovieBean> movies = (ArrayList<MovieBean>) movie.results;
        return movies;
    }

}
