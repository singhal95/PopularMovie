package com.android.praveer0809.popularmovie;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, MovieAdapter.ClickAdapterPosition {

    private RecyclerView recyclerView;
    private int NUMB_COLUMNS = 2;

    private int decision;
    public static int POSITION=0;
    private int MOST_POPULAR = 0;
    public static String FINAL_JSON_RESPONSE;
    private CharSequence[] options = {"MOST POPULAR", "HIGHEST RATED"};
    private MovieAdapter movieAdapter;
    private GridLayoutManager gridLayoutManager;
    public static ArrayList<MovieDetails> movieDetailsArrayList = new ArrayList<>();
    private TextView t1;//creating the object of the Gridview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvStudentList);
        t1 = (TextView) findViewById(R.id.interntet);
        gridLayoutManager=new GridLayoutManager(MainActivity.this,NUMB_COLUMNS);
        show(MOST_POPULAR);
    }

    /**
     * calls online function checks the internet connectivity and if it is true than initloder is called for doing background task
     *
     * @param position sets the option for mostpopular or highet rated
     */
    public void show(int position) {
        if (isOnline()) {
            decision = position;
            t1.setVisibility(View.GONE);
            recyclerView.setLayoutManager(gridLayoutManager);
            getSupportLoaderManager().initLoader(position, null, MainActivity.this);

        } else {
            t1.setVisibility(View.VISIBLE);
        }
    }

    /**
     * checks the network conectivity
     *
     * @return true if device is connected and false if not
     */
    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.highestrated == item.getItemId()) {
            createAlertbox();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * creates the alert box when the user clicks on the option menu
     */
    public void createAlertbox() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(MainActivity.this);
        dialogBox.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                show(which);
                dialog.cancel();
            }
        });
        dialogBox.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogBox.show();

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
                  FINAL_JSON_RESPONSE=null;
        if (id == MOST_POPULAR) {

            return new FetchMovieFromApi(MainActivity.this, getResources().getString(R.string.popularmovieurl));
        } else {

            return new FetchMovieFromApi(MainActivity.this, getResources().getString(R.string.highestrated));
        }

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (loader.getId() == decision) {
            FINAL_JSON_RESPONSE =data;
            movieDetailsArrayList.clear();
            new ParseMovieJsonResponse(data, MainActivity.this).fetchresults();
            movieAdapter = new MovieAdapter(MainActivity.this, movieDetailsArrayList);
            movieAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(movieAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        movieDetailsArrayList.clear();
        movieAdapter.notifyDataSetChanged();


    }

    /**
     * get the index of the arraylist on which the user clicked to see the details of the movie
     *
     * @param position index of arraylist
     */
    @Override
    public void getPosition(int position) {
        POSITION=position;
        Intent detailActivity = new Intent(MainActivity.this, MovieDetail.class);
        detailActivity.putExtra("ADAPTER POSITION", position);
        startActivity(detailActivity);

    }

    @Override
    protected void onResume() {
        gridLayoutManager.scrollToPosition(POSITION);
        super.onResume();
    }
}

