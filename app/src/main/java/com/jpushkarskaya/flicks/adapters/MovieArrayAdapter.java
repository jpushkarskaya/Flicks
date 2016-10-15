package com.jpushkarskaya.flicks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpushkarskaya.flicks.R;
import com.jpushkarskaya.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by epushkarskaya on 10/15/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data for position
        Movie movie = getItem(position);
        MovieHolder movieHolder;

        // check to see if reusing view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            movieHolder = new MovieHolder(convertView);
            convertView.setTag(movieHolder);
        }

        movieHolder = (MovieHolder) convertView.getTag();
        movieHolder.title.setText(movie.getTitle());
        movieHolder.overview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(movieHolder.poster);

        return convertView;
    }


    private class MovieHolder {

        TextView title;
        TextView overview;
        ImageView poster;

        public MovieHolder(View view) {
            title = (TextView) view.findViewById(R.id.tvTitle);
            overview = (TextView) view.findViewById(R.id.tvOverview);
            poster = (ImageView) view.findViewById(R.id.imgPoster);
        }

    }
}
