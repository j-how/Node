package com.djangohow.udacity.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.djangohow.udacity.R;
import com.djangohow.udacity.ui.PopularMovieActivity;
import com.djangohow.udacity.ui.fragment.DetailFragment;
import com.djangohow.udacity.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_popular_movie,bt_stock_hawk,bt_build_it_bigger,
            bt_make_yout_app_material,bt_go_ubiquitous,bt_capstone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView() {
        bt_popular_movie = (Button) findViewById(R.id.bt_popular_movie);
        bt_stock_hawk = (Button) findViewById(R.id.bt_stock_hawk);
        bt_build_it_bigger = (Button) findViewById(R.id.bt_build_it_bigger);
        bt_make_yout_app_material = (Button) findViewById(R.id.bt_make_yout_app_material);
        bt_go_ubiquitous = (Button) findViewById(R.id.bt_go_ubiquitous);
        bt_capstone = (Button) findViewById(R.id.bt_capstone);

    }


    public void initData() {
        bt_popular_movie.setOnClickListener(this);
        bt_stock_hawk.setOnClickListener(this);
        bt_build_it_bigger.setOnClickListener(this);
        bt_make_yout_app_material.setOnClickListener(this);
        bt_go_ubiquitous.setOnClickListener(this);
        bt_capstone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_popular_movie:
                intent = new Intent(this,PopularMovieActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_stock_hawk:
                break;
            case R.id.bt_build_it_bigger:
                break;
            case R.id.bt_make_yout_app_material:
                break;
            case R.id.bt_go_ubiquitous:
                break;
            case R.id.bt_capstone:
                break;
        }
    }

}
