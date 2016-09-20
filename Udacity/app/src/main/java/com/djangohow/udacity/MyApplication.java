package com.djangohow.udacity;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.djangohow.udacity.api.ApiEntity;
import com.djangohow.udacity.entity.Constant;
import com.djangohow.udacity.service.MyIntentService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.udacity.dao.DaoMaster;
import com.udacity.dao.DaoSession;

/**
 * Created by django on 2016/8/21.
 */
public class MyApplication extends Application{
    public static DaoSession daoSession;
    public static SQLiteDatabase db;
    public static DaoMaster.DevOpenHelper openHelper;
    public static DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        setupDatabase();
        updataData();
    }

    private void updataData() {
        MyIntentService.startActionPop(this, ApiEntity.PopURL+ Constant.PAGE);
        MyIntentService.startActionRated(this, ApiEntity.RatedURL+ Constant.PAGE);
    }

    private void setupDatabase() {
        openHelper = new DaoMaster.DevOpenHelper(this,"movie",null);
        db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
