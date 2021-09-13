package com.example.ddangnmarket.src.main.writing;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddangnmarket.R;

import java.util.ArrayList;

public class WritingAdapter extends RecyclerView.Adapter<WritingAdapter.ViewHolder> {
    private ArrayList<Uri> uriArrayList;
    private Context mContext;

    public WritingAdapter(ArrayList<Uri> uriArrayList, Context mContext) {
        this.uriArrayList = uriArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public WritingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.upload_image,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WritingAdapter.ViewHolder holder, int position) {
        Uri uri = uriArrayList.get(position);

        holder.mIvImage.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvImage =itemView.findViewById(R.id.writing_iv_upload_image);
            mIvImage.setClipToOutline(true);
        }
    }
}
