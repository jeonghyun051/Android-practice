package com.example.ddangnmarket.src.main.writing;

import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.writing.interfaces.WritingActivityView;
import com.example.ddangnmarket.src.main.writing.interfaces.WritingRetrofitInterface;
import com.example.ddangnmarket.src.main.writing.models.ProductResponse;
import com.example.ddangnmarket.src.main.writing.models.RequestProduct;
import com.example.ddangnmarket.src.main.writing.models.RequestProductImage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class WritingService {
    private final WritingActivityView mWritingActivityView;

    public WritingService(WritingActivityView mWritingActivityView) {
        this.mWritingActivityView = mWritingActivityView;
    }

    void postProduct(RequestProduct requestProduct) {
        final WritingRetrofitInterface writingRetrofitInterface = getRetrofit().create(WritingRetrofitInterface.class);
        writingRetrofitInterface.postProduct(requestProduct).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                if(productResponse == null){
                    mWritingActivityView.validateProductFailure(productResponse.getMessage());
                    return;
                }
                System.out.println("validateProductSuccess");
                mWritingActivityView.validateProductSuccess(productResponse.getIsSuccess(), productResponse.getCode(), productResponse.getMessage(), productResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mWritingActivityView.validateProductFailure(t.getMessage());
            }
        });
    }

    void postProductImage(final RequestProductImage requestProductImage, final int index) {
        final WritingRetrofitInterface writingRetrofitInterface = getRetrofit().create(WritingRetrofitInterface.class);
        writingRetrofitInterface.postProductImage(requestProductImage).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                mWritingActivityView.validateProductImageSuccess(productResponse.getIsSuccess(), productResponse.getCode(), productResponse.getMessage(), index);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mWritingActivityView.validateProductImageFailure(t.getMessage());
            }
        });
    }

}
