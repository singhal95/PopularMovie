package com.android.praveer0809.popularmovie;

/**
 * Created by NITIN on 19-Feb-17.
 */
public class MovieDetails {
    private String poster, summary, relasedate, title, backgroundpicture;
    private int movieid;
    private double ratings;

    public MovieDetails(String summary, String relasedate, String poster, String title, int movieid, String backgroundpicture, double ratings) {
        this.summary = summary;
        this.relasedate = relasedate;
        this.poster = poster;
        this.title = title;
        this.movieid = movieid;
        this.backgroundpicture = backgroundpicture;
        this.ratings = ratings;
    }


    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getRelasedate() {
        return relasedate;
    }

    public String getSummary() {
        return summary;
    }

    public String getBackgroundpicture() {
        return backgroundpicture;
    }

    public int getMovieid() {
        return movieid;
    }

    public double getRatings() {
        return ratings;
    }
}
