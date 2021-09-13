package com.example.ddangnmarket.src.main.home;

import android.util.Log;

import com.example.ddangnmarket.src.main.home.interfaces.ItemActivityView;
import com.example.ddangnmarket.src.main.home.interfaces.ItemRetrofitInterface;
import com.example.ddangnmarket.src.main.home.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ItemService {
    private final ItemActivityView mItemActivityView;

    public ItemService(ItemActivityView mItemActivityView) {
        this.mItemActivityView = mItemActivityView;
    }

    void getItem(String address, int page) {
        Log.d("로그",address);
        final ItemRetrofitInterface itemRetrofitInterface = getRetrofit().create(ItemRetrofitInterface.class);
        itemRetrofitInterface.getProductQuery(address, page).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                Log.d("로그",productResponse.getResultProduct().get(0).getAddress());
                mItemActivityView.validateItemSuccess(productResponse.getIsSuccess(), productResponse.getCode(), productResponse.getMessage(), productResponse.getResultProduct());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("로그",t.toString());
                mItemActivityView.validateItemFailure();
            }
        });
    }
}
