package com.djangohow.udacity.ui.fragment;


import android.net.Uri;
import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.djangohow.udacity.R;
import com.djangohow.udacity.vo.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView original_title,release_date,original_language,vote_average,overview;
    private ImageView poster_path;
    private SimpleDraweeView sdw_detail;
    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        original_title = (TextView) view.findViewById(R.id.original_title);
        release_date = (TextView) view.findViewById(R.id.release_date);
        original_language = (TextView) view.findViewById(R.id.original_language);
        vote_average = (TextView) view.findViewById(R.id.vote_average);
        overview = (TextView) view.findViewById(R.id.overview);

        poster_path = (ImageView) view.findViewById(R.id.poster_path);
//        sdw_detail = (SimpleDraweeView) view.findViewById(R.id.sdw_detail);
    }

    public void setValue(String url, MovieBean bean) {
        original_title.setText(bean.original_title);
        release_date.setText(bean.release_date);
        original_language.setText(bean.original_language);
        vote_average.setText(bean.vote_average+"/10");
        overview.setText(bean.overview);
        Picasso.with(getContext()).load(url).into(poster_path);
        Uri uri = Uri.parse(url);
//        sdw_detail.setImageURI(uri);
    }
}
