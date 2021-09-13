package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView gvMovie;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        List<Integer> movies = download();
        movieAdapter = new MovieAdapter(movies);
        gvMovie.setAdapter(movieAdapter);
    }

    private void init(){
        gvMovie = findViewById(R.id.gv_movie);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        gvMovie.setLayoutManager(gridLayoutManager);
    }

    // 가짜 다운로드
    private List<Integer> download(){
        List<Integer> movies = new ArrayList<>();
        movies.add(R.drawable.mov01);
        movies.add(R.drawable.mov02);
        movies.add(R.drawable.mov03);
        movies.add(R.drawable.mov04);
        movies.add(R.drawable.mov05);
        movies.add(R.drawable.mov06);
        movies.add(R.drawable.mov07);
        movies.add(R.drawable.mov08);
        movies.add(R.drawable.mov09);
        movies.add(R.drawable.mov10);
        movies.add(R.drawable.mov11);
        movies.add(R.drawable.mov12);
        return movies;
    }
}