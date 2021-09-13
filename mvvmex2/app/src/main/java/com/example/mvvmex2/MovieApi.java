package com.example.mvvmex2;

import com.example.mvvmex2.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("movies")
    Call<List<Movie>> getMovies();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
