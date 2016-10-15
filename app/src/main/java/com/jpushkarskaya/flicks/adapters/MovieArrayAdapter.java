package com.jpushkarskaya.flicks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpushkarskaya.flicks.R;
import com.jpushkarskaya.flicks.models.Movie;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;

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
        final Movie movie = getItem(position);
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

        // load poster image
       Picasso.with(getContext()).load(movie.getPosterPath())
                .transform(new RoundedCornersTransformation(5, 5))
                .placeholder(R.drawable.default_movie)
                .into(movieHolder.poster);



        final ImageView changingPoster = movieHolder.poster;

        movieHolder.poster.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .transform(new RoundedCornersTransformation(5, 5))
                        .placeholder(changingPoster.getDrawable())
                        .transform(new SwirlFilterTransformation(getContext()))
                        .into(changingPoster);
                return true;
            }

        });

        return convertView;
    }

    private class MovieHolder {

        TextView title;
        TextView overview;
        ImageView poster;

        public MovieHolder(View view) {
            title = (TextView) view.findViewById(R.id.tvTitle);
            title.setTypeface(EasyFonts.walkwayBold(getContext()));
            overview = (TextView) view.findViewById(R.id.tvOverview);
            poster = (ImageView) view.findViewById(R.id.imgPoster);
        }

    }
}
