package com.djangohow.udacity.ui;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.djangohow.udacity.R;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.ui.fragment.DetailFragment;
import com.djangohow.udacity.ui.fragment.PopMovieFragment;
import com.djangohow.udacity.utils.MessageEvent;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.udacity.bean.BeanMovie;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PopularMovieActivity extends AppCompatActivity {
    private PopMovieFragment mPopMovieFragment;
    private DetailFragment mDetailFragment;
    private Toolbar toolBar;
    private boolean isTwoPage = false;
    private FrameLayout fm_detail_moive;
    private FrameLayout fm_pop_moive;
    public  static boolean isDetailFragment = false;
    public  BeanMovie mMovie;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popular_movie);

        mPopMovieFragment = PopMovieFragment.newInstance(this);
        mDetailFragment = DetailFragment.newInstance(this);
        mMovie = DetailFragment.sMovie;

        fm_pop_moive = (FrameLayout) findViewById(R.id.fm_pop_moive);
        if(findViewById(R.id.fm_detail_moive)!=null){
            isTwoPage = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive,mPopMovieFragment).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_detail_moive,mDetailFragment).commit();

            fm_detail_moive = (FrameLayout) findViewById(R.id.fm_detail_moive);


        }else {
            isTwoPage = false;
            if (isDetailFragment){
                getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive, mDetailFragment).commit();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDetailFragment.setValue(mMovie.getPoster_path(), mMovie);
                    }
                }, 30);
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive, mPopMovieFragment).commit();
            }
        }
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setToolBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isDetailFragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive,mPopMovieFragment).commit();
        }
        isDetailFragment = false;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        }else {
            String hint = getResources().getString(R.string.suggest);
            Toast.makeText(PopularMovieActivity.this, hint, Toast.LENGTH_LONG).show();
        }
    }

    private void setToolBar() {
        String title = getResources().getString(R.string.toolbar_title);
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        toolBar.inflateMenu(R.menu.menu_main);
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorText));
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.menu_pop){
                    mPopMovieFragment.changeOrder(Constant.TYPE_POP);
                }else if(item.getItemId()==R.id.menu_rated){
                    mPopMovieFragment.changeOrder(Constant.TYPE_RATED);
                }else if(item.getItemId()==R.id.menu_favorite){
                    mPopMovieFragment.changeOrder(Constant.TYPE_FAVORITE);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final MessageEvent event){
        if(!isTwoPage) {
            getSupportFragmentManager().beginTransaction().hide(mPopMovieFragment).add(R.id.fm_pop_moive, mDetailFragment).addToBackStack(null).commit();
        }else {
            fm_pop_moive.setVisibility(View.VISIBLE);
            fm_detail_moive.setVisibility(View.VISIBLE);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDetailFragment.setValue(event.url,event.mBean);
            }
        },30);
    }
}
