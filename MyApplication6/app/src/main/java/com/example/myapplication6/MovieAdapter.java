package com.example.myapplication6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// 익명클래스 내부에서는 외부에 변수(스택)를 접근할 수는 있는데 변경할 수 없다.
// 전역변수로 만들면 다 해결되요!!
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private static final String TAG = "MovieAdapter";

    private final List<Integer> movies;

    public float saveRating = 0;

    public MovieAdapter(List<Integer> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item,  parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_item);

            ivItem.setOnClickListener(v -> {
                View dialog = v.inflate(v.getContext(), R.layout.dialog_item, null);
                ImageView ivItem = dialog.findViewById(R.id.iv_item);
                int pos = getAdapterPosition();
                ivItem.setImageResource(movies.get(pos));
                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());

                //추가
                SharedPreferences pref = v.getContext().getSharedPreferences("pref",MainActivity.MODE_PRIVATE);

                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                TextView tvRating = dialog.findViewById(R.id.rating);
                ratingBar.setRating(pref.getFloat("rating"+pos,0));
                tvRating.setText(pref.getFloat("rating"+pos,0)+"");


                ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                    Log.d(TAG, "MyViewHolder: rating"+rating);
                    tvRating.setText(rating+"");
                    saveRating = rating;
                });

                dlg.setTitle("큰 포스터");
                dlg.setIcon(R.drawable.movie_icon);
                dlg.setView(dialog);
                dlg.setNegativeButton("닫기",null);
                dlg.setPositiveButton("확인",(dialog1, which) -> {

                    SharedPreferences.Editor ed = pref.edit();
                    ed.putFloat("rating"+pos,saveRating);
                    ed.commit();
                });
                dlg.show();
            });
        }

        public void setItem(Integer i){
            ivItem.setImageResource(i);
        }
    }
}