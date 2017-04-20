package com.android.praveer0809.popularmovie;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NITIN on 19-Feb-17.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<MovieDetails> movieDetailsArrayList;
    private ClickAdapterPosition clikadapterposition;

    public MovieAdapter(Activity mainactivity, ArrayList<MovieDetails> movieDetailsArrayList) {

        this.context = mainactivity;
        clikadapterposition = (ClickAdapterPosition) mainactivity;
        this.movieDetailsArrayList = movieDetailsArrayList;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.single_item, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + movieDetailsArrayList.get(position).getPoster()).fit().into(holder.posterimage);
    }


    @Override
    public int getItemCount() {
        return movieDetailsArrayList.size();
    }

    public interface ClickAdapterPosition {
        public void getPosition(int positon);
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterimage;

        public MovieHolder(View itemView) {
            super(itemView);
            posterimage = (ImageView) itemView.findViewById(R.id.imageposter);
            posterimage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clikadapterposition.getPosition(position);
        }
    }
}
