package com.djangohow.udacity.api;

/**
 * Created by django on 2016/8/28.
 */
public class ApiEntity {
    public static final String KEY = "";
    public static final String PopURL = "http://api.themoviedb.org/3/movie/popular?api_key="+KEY+"&page=";
    public static final String RatedURL = "http://api.themoviedb.org/3/movie/top_rated?api_key="+KEY+"&page=";
    public static final String ImageURL = "http://image.tmdb.org/t/p/w185/";
}
