package com.djangohow.udacity.ui;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PopularMovieActivity extends AppCompatActivity {
    private PopMovieFragment mPopMovieFragment;
    private DetailFragment mDetailFragment;
    private Toolbar toolBar;
    private boolean isTwoPage = false;
    private FrameLayout fm_detail_moive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("tag", "onCreate: "+3);
        if (savedInstanceState!=null){
            Log.i("tag", "onCreate: "+4);
        }
        setContentView(R.layout.activity_popular_movie);
        Fresco.initialize(this);
        mPopMovieFragment = PopMovieFragment.newInstance(this);
        mDetailFragment = DetailFragment.newInstance(this);

        //宽高
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int hight = wm.getDefaultDisplay().getHeight();

//        if(findViewById(R.id.fm_rated_moive)!=null){
        if(width>hight){
            isTwoPage = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive,mPopMovieFragment).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_detail_moive,mDetailFragment).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fm_pop_moive,mPopMovieFragment).commit();
            if(findViewById(R.id.fm_detail_moive)!=null){
                fm_detail_moive = (FrameLayout) findViewById(R.id.fm_detail_moive);
                fm_detail_moive.setVisibility(View.GONE);
            }
        }

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
//                    mPopMovieFragment.changeOrder(ApiEntity.PopURL+ Constant.PAGE);
                    mPopMovieFragment.changeOrder(Constant.TYPE_POP);
                }else if(item.getItemId()==R.id.menu_rated){
//                    mPopMovieFragment.changeOrder(ApiEntity.RatedURL+Constant.PAGE);
                    mPopMovieFragment.changeOrder(Constant.TYPE_RATED);
                }else if(item.getItemId()==R.id.menu_favorite){
//                    mPopMovieFragment.changeOrder(ApiEntity.RatedURL+Constant.PAGE);
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        int type = PopMovieFragment.type_what;
        outState.putInt("type",type);
        super.onSaveInstanceState(outState, outPersistentState);
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
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDetailFragment.setValue(event.url,event.mBean);
            }
        },30);
    }
}
