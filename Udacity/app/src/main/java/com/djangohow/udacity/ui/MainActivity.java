package com.djangohow.udacity.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.djangohow.udacity.MyApplication;
import com.djangohow.udacity.R;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.db.PopSqliteOpenHelper;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.service.MyIntentService;
import com.udacity.bean.BeanMovie;
import com.udacity.dao.BeanMovieDao;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static MyApplication app;
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
                Toast.makeText(MainActivity.this, "hawk", Toast.LENGTH_SHORT).show();
//                Log.i("tag", "onClick: ");
//                intent = new Intent(this,PopularMovieActivity.class);
//                startActivity(intent);
                MyIntentService.startActionPop(this, ApiEntity.PopURL+ Constant.PAGE);
                break;
            case R.id.bt_build_it_bigger:

//                QueryBuilder qb =  app.daoSession.getBeanMovieDao().queryBuilder();
//                qb.where(BeanMovieDao.Properties.Movie_id.gt(0)).build();
//                ArrayList<BeanMovie> movies = (ArrayList<BeanMovie>) qb.list();
                Query qb = app.daoSession.getBeanMovieDao().queryBuilder().build();
                ArrayList<BeanMovie> movies = (ArrayList<BeanMovie>) qb.list();
                if (movies==null){
                    Log.i("tag", "movies is null");
                }else {
                    Log.i("tag", ""+movies.size());
                }
                for (BeanMovie movie:movies){
                    Log.i("tag", "onClick: "+movie.getTitle()+"  id: "+movie.getMovie_id());
                }
                Toast.makeText(MainActivity.this, "cursor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_make_yout_app_material:
                Query qb1 = app.daoSession.getBeanMovieDao().queryBuilder().
                        where(BeanMovieDao.Properties.Movie_id.eq(240)).build();
                ArrayList<BeanMovie> movies1 = (ArrayList<BeanMovie>) qb1.list();
                for (BeanMovie movie:movies1){
                    Log.i("tag", "onClick: "+movie.getTitle());
                }
                break;
            case R.id.bt_go_ubiquitous:
                break;
            case R.id.bt_capstone:
                break;
        }
    }

}
