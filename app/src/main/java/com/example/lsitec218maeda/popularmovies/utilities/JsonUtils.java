package com.example.lsitec218maeda.popularmovies.utilities;
import android.content.Context;

import com.example.lsitec218maeda.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by lsitec218.maeda on 09/11/17.
 */

public class JsonUtils {

    public static Movie[] getStringsFromJson(Context context, String jsonString)
            throws JSONException {

        final String MOVIE_RESULTS = "results";
        final String MOVIE_ID = "id";
        final String MOVIE_VOTE_COUNT = "vote_count";
        final String MOVIE_VIDEO = "video";
        final String MOVIE_VOTE_AVERAGE = "vote_average";
        final String MOVIE_TITLE = "title";
        final String MOVIE_POPULARITY = "popularity";
        final String MOVIE_POSTER_PATH = "poster_path";
        final String MOVIE_ORIGINAL_LANGUAGE = "original_language";
        final String MOVIE_ORIGINAL_TITLE = "original_title";
        final String MOVIE_BACKDROP_PATH = "backdrop_path";
        final String MOVIE_ADULT = "adult";
        final String MOVIE_OVERVIEW = "overview";
        final String MOVIE_RELEASE_DATE = "release_date";
        Movie[] Movies;

        JSONObject moviesJson = new JSONObject(jsonString);
        JSONArray moviesArray = moviesJson.getJSONArray(MOVIE_RESULTS);
        Movies = new Movie[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieData = moviesArray.getJSONObject(i);
            Movies[i]= new Movie(
                    movieData.getInt(MOVIE_ID),
                    movieData.getInt(MOVIE_VOTE_COUNT),
                    movieData.getBoolean(MOVIE_VIDEO),
                    movieData.getDouble(MOVIE_VOTE_AVERAGE),
                    movieData.getString(MOVIE_TITLE),
                    movieData.getDouble(MOVIE_POPULARITY),
                    movieData.getString(MOVIE_POSTER_PATH),
                    movieData.getString(MOVIE_ORIGINAL_LANGUAGE),
                    movieData.getString(MOVIE_ORIGINAL_TITLE),
                    movieData.getString(MOVIE_BACKDROP_PATH),
                    movieData.getBoolean(MOVIE_ADULT),
                    movieData.getString(MOVIE_OVERVIEW),
                    movieData.getString(MOVIE_RELEASE_DATE)
            );
        }
        return Movies;
    }
}
