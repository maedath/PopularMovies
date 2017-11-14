package com.example.lsitec218maeda.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lsitec218maeda.popularmovies.data.Movie;
import com.example.lsitec218maeda.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by lsitec218.maeda on 13/11/17.
 * Receives Movie instance from MainActivity to fill Movie details fields
 */
public class DetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mOverview;
    private TextView mVoteAverage;
    private TextView mReleaseDate;
    private ImageView mThumbnail;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = findViewById(R.id.tv_title);
        mOverview = findViewById(R.id.tv_overview);
        mVoteAverage = findViewById(R.id.tv_vote_average);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mThumbnail= findViewById(R.id.iv_thumbnail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            mMovie= (Movie) getIntent().getSerializableExtra("Movies");
            mTitle.setText(mMovie.getOriginalTitle());
            mOverview.setText(mMovie.getOverview());
            mVoteAverage.setText("Rating: " + String.valueOf(mMovie.getVoteAverage()) + "/10");
            mReleaseDate.setText("Release Date: " + mMovie.getReleaseDate());
            mTitle.setText(mMovie.getTitle());
            Picasso.with(mThumbnail.getContext()).load(String.valueOf(NetworkUtils.buildMoviePosterUrl("w92", mMovie.getPosterPath()))).into(mThumbnail);
        }
    }
}
