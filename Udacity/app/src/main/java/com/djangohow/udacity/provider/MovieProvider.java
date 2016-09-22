package com.djangohow.udacity.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.djangohow.udacity.MyApplication;
import com.udacity.dao.BeanMovieDao;
import com.udacity.dao.DaoMaster;
import com.udacity.dao.DaoSession;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

public class MovieProvider extends ContentProvider {

    static MyApplication app;
    static BeanMovieDao beanMovieDao;
    static DaoSession daoSession;
    static SQLiteDatabase db;
    static DaoMaster.DevOpenHelper openHelper;
    static DaoMaster daoMaster;
    private static UriMatcher sUriMatcher;
    private static final int MOVIE_DIR = 1;

    public static final String AUTHORITY = "com.django.udacity.provider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
//    private DaoMaster.DevOpenHelper openHelper;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,"BeanMovie",MOVIE_DIR);
    }
    public MovieProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        return "vnd.android.cursor.dir/vnd.com.django.udacity.provider.BeanMovie";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

//                ArrayList<PopMovie> movies = (ArrayList<PopMovie>) app.daoSession.getPopMovieDao().queryRaw(null,null);
//        ArrayList<PopMovie> movies  = (ArrayList<PopMovie>) app.daoSession.getPopMovieDao().queryBuilder().list();

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        app = new MyApplication();
//        beanMovieDao = app.daoSession.getBeanMovieDao();
//        daoSession = app.daoSession;
        db = app.db;
//        openHelper = app.openHelper;
//        daoMaster = app.daoMaster;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
//        // TODO: Implement this to handle query requests from clients.
//        int uriType = sUriMatcher.match(uri);
////        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        QueryBuilder queryBuilder = beanMovieDao.queryBuilder();
////        Query query = query()
//        switch (uriType){
//            case MOVIE_DIR:
////                queryBuilder.setTables("BeanMovie");
//
//                break;
//        }
//        Cursor cursor = queryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
//        cursor.setNotificationUri(getContext().getContentResolver(),uri);
//        return cursor;
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
