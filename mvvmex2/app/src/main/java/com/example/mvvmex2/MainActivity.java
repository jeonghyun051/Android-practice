package com.example.mvvmex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.mvvmex2.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private RecyclerView rvmovie;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApi movieApi = MovieApi.retrofit.create(MovieApi.class);
        Call<List<Movie>> call = movieApi.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
                rvmovie = findViewById(R.id.rv_movie);
                rvmovie.setLayoutManager(manager);
                movieAdapter = new MovieAdapter(movies);

                rvmovie.setAdapter(movieAdapter);
                Log.d(TAG, "onResponse:  " + movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Log.d(TAG, "onFailure: 실패");
            }
        });

    }
}