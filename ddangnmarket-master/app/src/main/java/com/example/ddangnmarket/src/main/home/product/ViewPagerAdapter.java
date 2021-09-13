package com.example.ddangnmarket.src.main.home.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> mImageList;

    public ViewPagerAdapter(Context mContext, ArrayList<String> mImageList) {
        this.mContext = mContext;
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_image, null);

        ImageView mImageView = view.findViewById(R.id.product_information_iv);
        Glide.with(mContext).load(mImageList.get(position)).into(mImageView);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }
}
