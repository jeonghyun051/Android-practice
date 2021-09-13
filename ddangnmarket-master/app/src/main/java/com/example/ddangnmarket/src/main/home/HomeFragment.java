package com.example.ddangnmarket.src.main.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.main.home.interfaces.ItemActivityView;
import com.example.ddangnmarket.src.main.home.models.ResultProduct;

import java.util.ArrayList;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class HomeFragment extends Fragment implements ItemActivityView {
    MainActivity activity;
    ListView mLvItemsList;
    ItemsAdapter itemsAdapter;
    ArrayList<ResultProduct> mResultProducts = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final String dong = sSharedPreferences.getString("dong", "");

        TextView mTvDong = view.findViewById(R.id.home_fragment_tv_dong);
        mTvDong.setText(dong);

        mLvItemsList = view.findViewById(R.id.home_lv_product);
        itemsAdapter = new ItemsAdapter(mResultProducts, activity);
        mLvItemsList.setAdapter(itemsAdapter);

        getItem(dong, 1);

        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getItem(dong, 1);
            }
        });


        return view;
    }

    public void getItem(String address, int page) {
        final ItemService itemService = new ItemService(this);
        itemService.getItem(address, page);
    }

    @Override
    public void validateItemSuccess(boolean isSuccess, int code, String message, ArrayList<ResultProduct> resultProducts) {
        if (isSuccess) {
            swipeRefreshLayout.setRefreshing(false);
            if (code == 100) {
                //activity.showCustomToast(message);

                mResultProducts.clear();
                mResultProducts.addAll(resultProducts);
                itemsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void validateItemFailure() {
        activity.showCustomToast("validateItemFailure");
    }
}
