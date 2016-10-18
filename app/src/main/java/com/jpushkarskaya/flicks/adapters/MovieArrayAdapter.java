package com.jpushkarskaya.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpushkarskaya.flicks.R;
import com.jpushkarskaya.flicks.models.Movie;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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
        movieHolder.btnDetails.setTag(movie);

        String imgPath;
        int placeholder;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imgPath = movie.getPosterPath();
            placeholder = R.drawable.default_movie;
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imgPath = movie.getBackdropPath();
            placeholder = R.drawable.default_movie_land;
        } else {
            throw new RuntimeException("Orientation " + orientation + " is invalid.");
        }

        // load poster image
       Picasso.with(getContext()).load(imgPath)
                .transform(new RoundedCornersTransformation(5, 5))
                .placeholder(placeholder)
                .into(movieHolder.poster);

        return convertView;
    }

    public class MovieHolder {

        public TextView title;
        public TextView overview;
        public ImageView poster;
        public ImageButton btnDetails;

        public MovieHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.tvTitle);
            this.title.setTypeface(EasyFonts.walkwayBold(getContext()));
            this.overview = (TextView) view.findViewById(R.id.tvOverview);
            this.poster = (ImageView) view.findViewById(R.id.imgPoster);
            this.btnDetails =  (ImageButton) view.findViewById(R.id.btnDetails);
        }

    }
}
