package com.djangohow.udacity.db;

import android.provider.BaseColumns;

/**
 * Created by django on 2016/9/12.
 */
public class MovieContract{



    public class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME_POP_MOVIE = "pop_movie";
        public static final String TABLE_NAME_RATED_MOVIE = "rated_movie";
        public static final String POSTER_PATH = "poster_path";
        public static final String ADULT = "adult";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String ID = "id";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String TITLE = "title";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_COUNT = "vote_count";
        public static final String VIDEO = "video";
        public static final String VOTE_AVERAGE = "vote_average";
    }
    public class MovieEntryGenre implements BaseColumns{
        public static final String TABLE_NAME_POP_MOVIE_GENRE_IDS = "pop_movie_genre_ids";
        public static final String TABLE_NAME_RATED_MOVIE_GENRE_IDS = "rated_movie_genre_ids";
        public static final String GENRE_ID = "genre_id";
        public static final String ORDER = "order_def";
        public static final String MOVIE_ID = "movie_id";
    }
}
