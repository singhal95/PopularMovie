package com.android.praveer0809.popularmovie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NITIN on 19-Feb-17.
 */
public class FetchMovieFromApi extends AsyncTaskLoader<String> {

    private StringBuilder JSON_RESPONSE = new StringBuilder();
    private String POPULAR_URL;
    private Context context;

    public FetchMovieFromApi(Context context, String POPULAR_URL) {
        super(context);
        this.context = context;
        this.POPULAR_URL = POPULAR_URL;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (MainActivity.FINAL_JSON_RESPONSE !=null){
            return;
        }
        else {
            forceLoad();
        }

    }

    @Override
    public String loadInBackground() {
        try {
            Log.i("tag10", "again");
            URL movieapiurl = new URL(POPULAR_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) movieapiurl.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int read;
            while ((read = bufferedReader.read()) != -1) {
                char singlecharacter = (char) read;
                JSON_RESPONSE.append(singlecharacter);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("tag7", JSON_RESPONSE.toString());
        return JSON_RESPONSE.toString();
    }
}

