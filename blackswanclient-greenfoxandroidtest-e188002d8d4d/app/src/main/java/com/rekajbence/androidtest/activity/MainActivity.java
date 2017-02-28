package com.rekajbence.androidtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.greenfox.androidtest.R;
import com.rekajbence.androidtest.adapter.MovieListAdapter;
import com.rekajbence.androidtest.models.LoadPopularMoviesResponse;
import com.rekajbence.androidtest.models.Movie;
import com.rekajbence.androidtest.network.MovieDbManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    RecyclerView recyclerView;
    MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieListAdapter(this);
        recyclerView.setAdapter(adapter);

        MovieDbManager.getInstance().loadPopularMovies(1, new Callback<LoadPopularMoviesResponse>() {
            @Override
            public void onResponse(Call<LoadPopularMoviesResponse> call, Response<LoadPopularMoviesResponse> response) {
                adapter.setMovieList((ArrayList<Movie>) response.body().getMovies());
                adapter.notifyItemInserted(adapter.getItemCount());

                Toast.makeText(MainActivity.this, "Successful load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoadPopularMoviesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
