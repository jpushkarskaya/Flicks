package com.jpushkarskaya.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

/**
 * Created by epushkarskaya on 10/17/16.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tvSplash = (TextView) findViewById(R.id.tvSplash);
        TextView tvCreator = (TextView) findViewById(R.id.tvCreator);

        try {
            tvSplash.setTypeface(EasyFonts.walkwayBold(this));
            tvCreator.setTypeface(EasyFonts.walkwayBold(this));
        } catch (NullPointerException ex){
            // ruh roh. no cool font for you
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MovieActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
