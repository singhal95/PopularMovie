package com.android.praveer0809.popularmovie;

import android.provider.BaseColumns;

/**
 * Created by NITIN on 14-Mar-17.
 */
public class FavoriteContractClass {
    public static class MovieEntry implements BaseColumns{
        public static final String TABLE_NAME="entry";
        public static final String MOVIE_ID="id";
        public static final String MOVIE_POSTER="poster";
        public static final String MOVIE_OVERVIEW="oveview";
        public static final String MOVIE_RATING="rating";
        public static final String MOVIE_RELEASEDATE="rdate";
    }
}
