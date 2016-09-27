package com.djangohow.udacity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djangohow.udacity.MyApplication;
import com.djangohow.udacity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static MyApplication app;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.bt_popular_movie)
    Button mBtPopularMovie;
    @BindView(R.id.bt_stock_hawk)
    Button mBtStockHawk;
    @BindView(R.id.bt_build_it_bigger)
    Button mBtBuildItBigger;
    @BindView(R.id.bt_make_yout_app_material)
    Button mBtMakeYoutAppMaterial;
    @BindView(R.id.bt_go_ubiquitous)
    Button mBtGoUbiquitous;
    @BindView(R.id.bt_capstone)
    Button mBtCapstone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_popular_movie, R.id.bt_stock_hawk, R.id.bt_build_it_bigger, R.id.bt_make_yout_app_material, R.id.bt_go_ubiquitous, R.id.bt_capstone})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.bt_popular_movie:
                intent = new Intent(this, PopularMovieActivity.class);
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
