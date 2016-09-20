package com.djangohow.udacity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by django on 2016/9/12.
 */
public class RatedSqliteOpenHelper extends SQLiteOpenHelper {
    //rated movie表
    final String SQL_CREATE_RATED_MOVIE_TABLE =  "create table"+ MovieContract.MovieEntry.TABLE_NAME_RATED_MOVIE + "(" +
            MovieContract.MovieEntry._ID + "integer primary key autoincrement," +
            MovieContract.MovieEntry.POSTER_PATH + "text" +
            MovieContract.MovieEntry.ADULT + "blob" +
            MovieContract.MovieEntry.OVERVIEW + "text" +
            MovieContract.MovieEntry.RELEASE_DATE + "text" +
            MovieContract.MovieEntry.ID + "integer" +
            MovieContract.MovieEntry.ORIGINAL_TITLE + "text" +
            MovieContract.MovieEntry.ORIGINAL_LANGUAGE + "text" +
            MovieContract.MovieEntry.TITLE + "text" +
            MovieContract.MovieEntry.BACKDROP_PATH + "text" +
            MovieContract.MovieEntry.POPULARITY + "real" +
            MovieContract.MovieEntry.VOTE_COUNT + "integer" +
            MovieContract.MovieEntry.VIDEO + "blob" +
            MovieContract.MovieEntry.VOTE_AVERAGE + "real)" ;
    //rated movie中genre_ids:数组表
    final String SQL_CREATE_RATED_MOVIE_GENRE_IDS_TABLE = "create table"+ MovieContract.MovieEntryGenre.TABLE_NAME_RATED_MOVIE_GENRE_IDS+"(" +
            MovieContract.MovieEntryGenre._ID + "integer primary key autoincrement," +
            MovieContract.MovieEntryGenre.GENRE_ID + "integer," + //数值
            MovieContract.MovieEntryGenre.ORDER + "integer" +//排序序号
            "foreign key (" + MovieContract.MovieEntryGenre.MOVIE_ID + ") references " +//外键指向movie
            MovieContract.MovieEntry.TABLE_NAME_RATED_MOVIE +
            "("+ MovieContract.MovieEntry.ID +"))";


    public RatedSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(SQL_CREATE_RATED_MOVIE_TABLE);
            db.execSQL(SQL_CREATE_RATED_MOVIE_GENRE_IDS_TABLE);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction(); // 结束事务
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
