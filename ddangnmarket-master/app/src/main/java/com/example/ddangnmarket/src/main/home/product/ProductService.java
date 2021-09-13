package com.example.ddangnmarket.src.main.home.product;

import com.example.ddangnmarket.src.main.home.product.interfaces.ProductActivityView;
import com.example.ddangnmarket.src.main.home.product.interfaces.ProductRetrofitInterface;
import com.example.ddangnmarket.src.main.home.product.models.ProductAnotherResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ProductService {
    private final ProductActivityView productActivityView;

    public ProductService(ProductActivityView productActivityView) {
        this.productActivityView = productActivityView;
    }

    void getProduct(int productNo) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductPath(productNo).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                productActivityView.validateProductSuccess(productResponse.getIsSuccess(), productResponse.getCode(), productResponse.getMessage(), productResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productActivityView.validateProductFailure(t.getMessage());
            }
        });
    }

    void getProductImage(int productNo) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductImagePath(productNo).enqueue(new Callback<ProductImageResponse>() {
            @Override
            public void onResponse(Call<ProductImageResponse> call, Response<ProductImageResponse> response) {
                final ProductImageResponse productImageResponse = response.body();
                productActivityView.validateProductImageSuccess(productImageResponse.getIsSuccess(), productImageResponse.getCode(), productImageResponse.getMessage(), productImageResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProductImageResponse> call, Throwable t) {
                productActivityView.validateProductImageFailure(t.getMessage());
            }
        });
    }

    void getProductAnother(int userNo) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductAnotherPath(userNo).enqueue(new Callback<ProductAnotherResponse>() {
            @Override
            public void onResponse(Call<ProductAnotherResponse> call, Response<ProductAnotherResponse> response) {
                final ProductAnotherResponse productAnotherResponse = response.body();
                productActivityView.validateProductAnotherSuccess(productAnotherResponse.getIsSuccess(), productAnotherResponse.getCode(), productAnotherResponse.getMessage(), productAnotherResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProductAnotherResponse> call, Throwable t) {
                productActivityView.validateProductAnotherFailure(t.getMessage());
            }
        });
    }
}
