package com.jpushkarskaya.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jpushkarskaya.flicks.adapters.MovieArrayAdapter;
import com.jpushkarskaya.flicks.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String BACKDROP = "backdrop";
    public static final String OVERVIEW = "overview";
    public static final String RATING = "rating";
    public static final String RELEASE = "release";

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvMovies.setAdapter(movieAdapter);

        setupFonts();
        populateMovies();
    }

    public void setupFonts() {
        TextView tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvHeader.setTypeface(EasyFonts.walkwayBold(this));
    }

    public void populateMovies() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.removeAll(movies);
                    movies.addAll(Movie.fromJsonArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", movieJsonResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public void onDetailsClick(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Movie movie = (Movie) view.getTag();
        intent.putExtra(TITLE, movie.getTitle());
        intent.putExtra(BACKDROP, movie.getBackdropPath());
        intent.putExtra(OVERVIEW, movie.getOverview());
        intent.putExtra(RATING, movie.getPopularity());
        intent.putExtra(RELEASE, movie.getReleaseDate());
        startActivity(intent);
    }

}
