package com.example.ddangnmarket.src.main.home.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.home.product.models.ProductAnotherResponse;

import java.util.ArrayList;

import static com.example.ddangnmarket.src.main.home.ItemsAdapter.moneyFormatToWon;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<ProductAnotherResponse.Result> resultArrayList;
    private LayoutInflater mInflater;
    private Context mContext;

    public GridViewAdapter(ArrayList<ProductAnotherResponse.Result> resultArrayList, Context mContext) {
        this.resultArrayList = resultArrayList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return resultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return resultArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.girdview_product, parent, false);
        final ProductAnotherResponse.Result result = resultArrayList.get(position);

        ImageView imageView = convertView.findViewById(R.id.product_information_iv_another1);
        TextView mTvTitle = convertView.findViewById(R.id.product_information_tv_title_another1);
        TextView mTvPrice = convertView.findViewById(R.id.product_information_tv_price_another1);

        Glide.with(mContext).load(result.getImageUrl()).into(imageView);
        imageView.setClipToOutline(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mTvTitle.setText(result.getTitle());
        String tmp = moneyFormatToWon(result.getPrice());
        mTvPrice.setText(tmp + "원");

        return convertView;
    }

    public static String rerollConverter(int reroll){
        if(reroll<60){
            return reroll+"분 전";
        }else if(reroll<1440){
            return reroll/60+"시간 전";
        }else{
            return reroll/1440+"일 전";
        }
    }
}
