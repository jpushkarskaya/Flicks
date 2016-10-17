package com.jpushkarskaya.flicks;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

    SwipeRefreshLayout swipeContainer;
    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvMovies.setAdapter(movieAdapter);

        setupFonts();
        setUpRefresh();
        populateMovies();
    }

    public void setupFonts() {
        TextView tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvHeader.setTypeface(EasyFonts.walkwayBold(this));
    }

    public void setUpRefresh() {

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                populateMovies();
                swipeContainer.setColorSchemeResources(R.color.colorAccent,
                        R.color.colorAccentLight, R.color.colorPrimaryLight,
                        R.color.colorPrimary);
            }


        });
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
        // todo: navigate to a details screen
    }
}
