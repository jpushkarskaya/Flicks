package com.jpushkarskaya.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

    TextView tvMovieTitle;
    ImageView imgBackdrop;
    TextView tvOverview;
    TextView tvRating;
    TextView tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvMovieTitle.setTypeface(EasyFonts.walkwayBold(this));
        imgBackdrop = (ImageView) findViewById(R.id.imgBackdrop);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvRating = (TextView) findViewById(R.id.tvRating);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);

        populateViews();
    }

    public void populateViews() {
        tvMovieTitle.setText(getIntent().getStringExtra(MovieActivity.TITLE));
        String imgPath = getIntent().getStringExtra(MovieActivity.BACKDROP);
        Picasso.with(this).load(imgPath)
                .transform(new RoundedCornersTransformation(5, 5))
                .placeholder(R.drawable.default_movie_land)
                .into(imgBackdrop);

        tvOverview.setText(getIntent().getStringExtra(MovieActivity.OVERVIEW));
        tvRating.setText(getIntent().getStringExtra(MovieActivity.RATING));
        tvReleaseDate.setText(getIntent().getStringExtra(MovieActivity.RELEASE));
    }

    public void onExit(View view) {
        DetailsActivity.this.finish();
    }

}
