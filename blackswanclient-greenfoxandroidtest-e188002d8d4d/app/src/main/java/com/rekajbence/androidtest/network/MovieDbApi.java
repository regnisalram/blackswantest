package com.rekajbence.androidtest.network;

import com.rekajbence.androidtest.models.LoadPopularMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {

    @GET("movie/popular")
    Call<LoadPopularMoviesResponse> getPopularMovies(@Query("page") int page);
}
