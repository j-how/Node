package com.djangohow.udacity.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.djangohow.udacity.MyApplication;

public class MovieProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUERY_POP_SUCCESS = 1;
    private static final int QUERY_RATED_SUCCESS = 2;
//    private DaoMaster.DevOpenHelper openHelper;
    private SQLiteDatabase db;
    static {
        sUriMatcher.addURI("com.django.provider","pop",QUERY_POP_SUCCESS);
        sUriMatcher.addURI("com.django.provider","rated",QUERY_RATED_SUCCESS);
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
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

//                ArrayList<PopMovie> movies = (ArrayList<PopMovie>) app.daoSession.getPopMovieDao().queryRaw(null,null);
//        ArrayList<PopMovie> movies  = (ArrayList<PopMovie>) app.daoSession.getPopMovieDao().queryBuilder().list();
//        Log.i("tag", "onClick: "+movies.size());
//        Log.i("tag", "onClick: "+movies.get(0).toString());
//        Log.i("tag", "onClick: "+movies.get(0).getBackdrop_path());
//        Log.i("tag", "onClick: "+movies.get(0).getGenre_ids());
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
//        openHelper = MyApplication.getApplication().openHelper;
//        db = MyApplication.db;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        int code = sUriMatcher.match(uri);
        if (code == QUERY_POP_SUCCESS) {
            Cursor cursor = db.query("PopMovie", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        }else if (code == QUERY_RATED_SUCCESS){
            Cursor cursor = db.query("RatedMovie", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        } else {
            throw new UnsupportedOperationException("Uri 路径不匹配");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
