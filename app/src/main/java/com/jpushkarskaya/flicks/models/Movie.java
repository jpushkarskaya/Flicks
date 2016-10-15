package com.jpushkarskaya.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by epushkarskaya on 10/15/16.
 */

public class Movie {

    private final String BASE_PATH = "https://image.tmdb.org/t/p/w342";

    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private int popularity;

    public Movie(JSONObject object) throws JSONException {
        this.posterPath = object.getString("poster_path");
        this.backdropPath = object.getString("backdrop_path");
        this.title = object.getString("original_title");
        this.overview = object.getString("overview");
        this.popularity = object.getInt("popularity");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("%s%s", BASE_PATH, posterPath);
    }

    public String getBackdropPath() {
        return backdropPath == null ? null : String.format("%s%s", BASE_PATH, backdropPath);
    }

    public int getPopularity() {
        return popularity;
    }

}
