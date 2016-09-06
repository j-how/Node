package com.djangohow.udacity.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.djangohow.udacity.R;
import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.ui.fragment.DetailFragment;
import com.djangohow.udacity.ui.fragment.PopMovieFragment;
import com.djangohow.udacity.utils.MessageEvent;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PopularMovieActivity extends AppCompatActivity {
    private PopMovieFragment mPopMovieFragment;
    private DetailFragment mDetailFragment;
    private Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie);
        Fresco.initialize(this);
        mPopMovieFragment = PopMovieFragment.newInstance();
        mDetailFragment = DetailFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive,mPopMovieFragment).commit();
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setToolBar();
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
                    mPopMovieFragment.changeOrder(ApiEntity.PopURL+ Constant.PAGE);
                }else if(item.getItemId()==R.id.menu_rated){
                    mPopMovieFragment.changeOrder(ApiEntity.RatedURL+Constant.PAGE);
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
        getSupportFragmentManager().beginTransaction().hide(mPopMovieFragment).add(R.id.fm_pop_moive,mDetailFragment).addToBackStack(null).commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDetailFragment.setValue(event.url,event.mBean);
            }
        },30);
    }
}
