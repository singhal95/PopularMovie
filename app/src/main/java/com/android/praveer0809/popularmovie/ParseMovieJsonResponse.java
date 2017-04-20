package com.android.praveer0809.popularmovie;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NITIN on 19-Feb-17.
 */
public class ParseMovieJsonResponse {
    private String jsonrespose, poster, summary, relasedate, title, backgroundpicture;
    private Context context;
    private JSONObject mainobject, singlemovieobject;
    private JSONArray resultarray;
    private int movieid;
    private double ratings;

    /**
     * Constructor used to get the value of jsonresponse and context object from the mainctivity
     *
     * @param jsonrespose string used to store the api response
     * @param context     context object
     */

    public ParseMovieJsonResponse(String jsonrespose, Context context) {
        this.jsonrespose = jsonrespose;
        this.context = context;
    }

    /**
     * function used to fetch the results from the moviedb api
     */
    public void fetchresults() {
        Resources resources = context.getResources();
        try {
            mainobject = new JSONObject(jsonrespose);
            resultarray = mainobject.getJSONArray(resources.getString(R.string.result));
            for (int i = 0; i < resultarray.length(); i++) {
                singlemovieobject = resultarray.optJSONObject(i);
                poster = singlemovieobject.getString(resources.getString(R.string.Poster));
                movieid = singlemovieobject.getInt(resources.getString(R.string.movieid));
                ratings = singlemovieobject.getDouble(resources.getString(R.string.ratings));
                summary = singlemovieobject.getString(resources.getString(R.string.overview));
                relasedate = singlemovieobject.getString(resources.getString(R.string.relase_date));
                title = singlemovieobject.getString(resources.getString(R.string.title));
                backgroundpicture = singlemovieobject.getString(resources.getString(R.string.backgroundpitchure));
                MainActivity.movieDetailsArrayList.add(new MovieDetails(summary, relasedate, poster, title, movieid, backgroundpicture, ratings));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
