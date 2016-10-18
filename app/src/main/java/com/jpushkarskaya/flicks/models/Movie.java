package com.jpushkarskaya.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by epushkarskaya on 10/15/16.
 */

public class Movie {

    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String popularity;
    private String releaseDate;

    public Movie(JSONObject object) throws JSONException {
        this.posterPath = object.getString("poster_path");
        this.backdropPath = object.getString("backdrop_path");
        this.title = object.getString("title");
        this.overview = object.getString("overview");
        this.popularity = object.getString("popularity");
        this.releaseDate = object.getString("release_date");
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
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getBackdropPath() {
        return backdropPath == null ? null : String.format("https://image.tmdb.org/t/p/w780%s", backdropPath);
    }

    public String getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}