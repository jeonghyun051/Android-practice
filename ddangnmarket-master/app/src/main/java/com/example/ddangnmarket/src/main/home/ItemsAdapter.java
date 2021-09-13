package com.example.ddangnmarket.src.main.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.home.models.ResultProduct;
import com.example.ddangnmarket.src.main.home.product.ProductActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;
import static com.example.ddangnmarket.src.main.home.product.GridViewAdapter.rerollConverter;

public class ItemsAdapter extends BaseAdapter {
    private ArrayList<ResultProduct> mItemsList;
    private LayoutInflater mInflater;
    private Context mContext;
    HomeFragment homeFragment;

    public ItemsAdapter(ArrayList<ResultProduct> mItemsList, Context mContext) {
        this.mItemsList = mItemsList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.product_data, parent, false);
        final ResultProduct items = mItemsList.get(position);

        final ImageView image = convertView.findViewById(R.id.home_iv_product_pic);
        final TextView name = convertView.findViewById(R.id.home_tv_product_name);
        TextView address = convertView.findViewById(R.id.home_tv_address);
        TextView reroll = convertView.findViewById(R.id.home_tv_reroll);
        TextView price = convertView.findViewById(R.id.home_tv_price);
        TextView reply = convertView.findViewById(R.id.home_tv_reply);
        TextView interest = convertView.findViewById(R.id.home_tv_interest);

        String[] splitDong = items.getAddress().split(" ");
        String dong = splitDong[splitDong.length - 1];

        Glide.with(mContext).load(items.getImageUrl()).into(image);
        image.setClipToOutline(true);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        name.setText(items.getTitle());
        address.setText(dong);
        String tmp1 = rerollConverter(items.getReroll());
        reroll.setText(" · " + tmp1);
        reply.setText(items.getComments() + "");
        interest.setText(items.getFavorite() + "");

        String tmp = moneyFormatToWon(items.getPrice());
        price.setText(tmp + "원");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductActivity.class);
                intent.putExtra("productNo", items.getProductNo());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public static String moneyFormatToWon(int inputMoney) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(inputMoney);
    }

}
