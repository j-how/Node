package com.djangohow.udacity.api;

/**
 * Created by django on 2016/8/28.
 */
public class ApiEntity {
    public static final String KEY = "";
    public static final String PopURL = "http://api.themoviedb.org/3/movie/popular?api_key="+KEY+"&page=";
    public static final String RatedURL = "http://api.themoviedb.org/3/movie/top_rated?api_key="+KEY+"&page=";
    public static final String ImageURL = "http://image.tmdb.org/t/p/w185/";
    public static final String YouTubeURL = "https://www.youtube.com/watch?v=";
    public static final String BaseURL = "http://api.themoviedb.org/3/movie/";
    public static final String VideoURL = "/videos?api_key="+KEY;
    public static final String ReviewsURL = "/reviews?api_key="+KEY;

}
