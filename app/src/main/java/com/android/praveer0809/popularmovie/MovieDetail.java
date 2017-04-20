package com.android.praveer0809.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {


    private int clickedAdapterposition;
    private MovieDetails movieDetails;
    private RatingBar favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent detailactivity = getIntent();
        clickedAdapterposition = detailactivity.getIntExtra(getResources().getString(R.string.adapterpositon), 0);
        movieDetails = getObject();
        getSupportActionBar().setTitle(movieDetails.getTitle());
        setRating((TextView) findViewById(R.id.rating));
        setPoster((ImageView) findViewById(R.id.poster));
        setBackgroundImage((ImageView) findViewById(R.id.backgroundimage));
        setReleaseDate((TextView) findViewById(R.id.relase_date));
        setOverview((TextView) findViewById(R.id.overview));
        setMovieTitle((TextView) findViewById(R.id.movietitle));
        favorite= (RatingBar) findViewById(R.id.favorite);
        favorite.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MovieDetail.this,"clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * get the instance of the moviedetails class from the adapterindex on which the user clicked
     *
     * @return object of the moviedetails class
     */
    public MovieDetails getObject() {
        return MainActivity.movieDetailsArrayList.get(clickedAdapterposition);
    }

    /**
     * set the rating of the movie and presents in the stars rating of the movie
     *
     * @param rating ratingbarview to set the rating
     */
    public void setRating(TextView rating) {
        rating.setText(String.valueOf(movieDetails.getRatings()) + "/10");
    }

    /**
     * set the image poster of the movie
     *
     * @param poster imageview to set the poster
     */

    public void setPoster(ImageView poster) {
        Picasso.with(MovieDetail.this).load("http://image.tmdb.org/t/p/w185/" + movieDetails.getPoster()).fit().into(poster);
    }

    /**
     * sets the background image of the toolarbar
     *
     * @param backgroundImage imageview to set the image
     */
    public void setBackgroundImage(ImageView backgroundImage) {
        Picasso.with(MovieDetail.this).load("http://image.tmdb.org/t/p/w185/" + movieDetails.getBackgroundpicture()).fit().into(backgroundImage);
    }

    /**
     * set the relase date of the movie
     *
     * @param releaseDate textview to set the date
     */
    public void setReleaseDate(TextView releaseDate) {
        releaseDate.setText(movieDetails.getRelasedate());
    }

    /**
     * set the name of the movie
     *
     * @param title textview to set the name
     */
    public void setMovieTitle(TextView title) {
        title.setText(movieDetails.getTitle());
    }

    /**
     * sets the summary of the moview
     *
     * @param overview textview to set for summary
     */
    public void setOverview(TextView overview) {
        overview.setText(movieDetails.getSummary());
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
