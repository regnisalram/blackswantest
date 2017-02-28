package com.rekajbence.androidtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.R;
import com.rekajbence.androidtest.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by regnisalram on 2/27/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    ArrayList<Movie> movieList = new ArrayList<>();

    public MovieListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
        notifyItemRangeChanged(0, movieList.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie currentMovie = movieList.get(position);

        Picasso.with(holder.poster.getContext())
                .load(currentMovie.getPosterPath())
                .resize(150, 200)
                .into(holder.poster);

        holder.title.setText(currentMovie.getTitle());
        holder.rating.setText(String.valueOf(currentMovie.getAverageVote()));
        //holder.genres.setText(currentMovie.); TODO
        holder.genres.setText("blabla, blabla");
        holder.releaseYear.setText(currentMovie.getReleaseDateText());
        holder.description.setText(currentMovie.getOverview());
        holder.moreInfo.setText(R.string.more_info);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView poster, star, calendar;
        TextView title, rating, genres, releaseYear, description, moreInfo;

        public MyViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);

            star = (ImageView) itemView.findViewById(R.id.star);
            calendar = (ImageView) itemView.findViewById(R.id.calendar);

            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            genres = (TextView) itemView.findViewById(R.id.genres);
            releaseYear = (TextView) itemView.findViewById(R.id.release_year);
            description = (TextView) itemView.findViewById(R.id.description);
            moreInfo = (TextView) itemView.findViewById(R.id.more_info);
        }
    }
}
