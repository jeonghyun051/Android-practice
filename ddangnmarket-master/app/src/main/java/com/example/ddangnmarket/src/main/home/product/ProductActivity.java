package com.example.ddangnmarket.src.main.home.product;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.chat.ChatActivity;
import com.example.ddangnmarket.src.main.home.product.interfaces.ProductActivityView;
import com.example.ddangnmarket.src.main.home.product.models.ProductAnotherResponse;
import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.home.product.models.Result;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.example.ddangnmarket.src.main.home.ItemsAdapter.moneyFormatToWon;
import static com.example.ddangnmarket.src.main.home.product.GridViewAdapter.rerollConverter;

public class ProductActivity extends BaseActivity implements ProductActivityView {

    private ArrayList<String> mImageList;
    ImageView mImageView;
    ImageButton mBack, mShare, mMore;
    Button mBtnPurchase;
    TextView mTvNickname, mTvAddress, mTvManner, mTvtitle, mTvCategories, mTvReroll, mTvText, mTvChat, mTvFavorite, mTvHits, mTvPrice, mTvNicknameAnother;
    int productNo;
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    CircleIndicator mIndicator;
    GridView mGridView;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        mImageView = findViewById(R.id.product_information_iv);
        mTvNickname = findViewById(R.id.product_information_tv_nickname);
        mTvAddress = findViewById(R.id.product_information_tv_address);
        mTvManner = findViewById(R.id.product_information_tv_manner);
        mTvtitle = findViewById(R.id.product_information_tv_title);
        mTvCategories = findViewById(R.id.product_information_tv_categories);
        mTvReroll = findViewById(R.id.product_information_tv_reroll);
        mTvText = findViewById(R.id.product_information_tv_text);
        mTvChat = findViewById(R.id.product_information_tv_chat);
        mTvFavorite = findViewById(R.id.product_information_tv_favorite);
        mTvHits = findViewById(R.id.product_information_tv_hits);
        mTvPrice = findViewById(R.id.product_information_tv_price);
        mGridView = findViewById(R.id.product_information_gridview);
        mTvNicknameAnother = findViewById(R.id.product_information_tv_nickname_another);
        mScrollView = findViewById(R.id.product_information_scrollview);
        mBtnPurchase = findViewById(R.id.product_information_btn_purchase);


        mBack = findViewById(R.id.product_information_iv_back);
        mShare = findViewById(R.id.product_information_iv_share);
        mMore = findViewById(R.id.product_information_iv_more);

        Intent intent = getIntent();
        productNo = intent.getIntExtra("productNo", 1);


        //여기서 부터 뷰페이저
        mImageList = new ArrayList();

        mViewPager = findViewById(R.id.product_information_viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(this, mImageList);
        mViewPager.setAdapter(mViewPagerAdapter);

        //상단바
        mBack.setImageResource(R.drawable.home_as_up);
        mBack.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mShare.setImageResource(R.drawable.ic_share_outline_24);
        mShare.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mMore.setImageResource(R.drawable.icon_ads_more);
        mMore.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);

        //인디케이터
        mIndicator = findViewById(R.id.product_information_indicator);


        getProduct(productNo);

        //스크롤

//        mGridView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mScrollView.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
    }

    public void productInformationOnClick(View view) {
        switch (view.getId()) {
            case R.id.product_information_iv_back:
                onBackPressed();
                break;
            case R.id.product_information_iv_share:
                //share();
                break;
            case R.id.product_information_iv_more:
                // more();
                break;
            case R.id.product_information_btn_purchase:
                purchase(productNo);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void purchase(int productNo) {
        Intent intent = new Intent(ProductActivity.this, ChatActivity.class);
        intent.putExtra("productNo", productNo);
        startActivity(intent);
    }

    public void getProduct(int productNo) {
        final ProductService productService = new ProductService(this);
        productService.getProduct(productNo);
        productService.getProductImage(productNo);
    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, Result result) {
        if (isSuccess) {
            if (code == 100) {
                //showCustomToast(message);

                mTvNickname.setText(result.getId());
                mTvAddress.setText(result.getAddress());
                mTvManner.setText(result.getManner() + "°C");
                mTvtitle.setText(result.getTitle());
                mTvCategories.setText(result.getCategories());
                String tmp = rerollConverter(result.getReroll());
                mTvReroll.setText(" · " + tmp);
                mTvText.setText(result.getText());
                mTvChat.setText("채팅 " + result.getChat() + "개");
                mTvFavorite.setText(" · 관심 " + result.getFavorite() + "개");
                mTvHits.setText(" · 조회 " + result.getHits() + "");
                String mPrice = moneyFormatToWon(result.getPrice());
                mTvPrice.setText(mPrice + "원");
                mTvNicknameAnother.setText(result.getId() + "님의 판매 상품");

                final ProductService productService = new ProductService(this);
                productService.getProductAnother(result.getUserNo());
            } else showCustomToast(message);
        }
    }

    @Override
    public void validateProductFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProductImageSuccess(boolean isSuccess, int code, String message, ArrayList<ProductImageResponse.Result> resultArrayList) {
        //showCustomToast(message);

        mImageList.clear();
        for (int i = 0; i < resultArrayList.size(); i++) {
            mImageList.add(resultArrayList.get(i).getImageUrl());
        }
        mViewPagerAdapter.notifyDataSetChanged();
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void validateProductImageFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProductAnotherSuccess(boolean isSuccess, int code, String message, ArrayList<ProductAnotherResponse.Result> resultArrayList) {


        //그리드뷰
        GridViewAdapter gridViewAdapter = new GridViewAdapter(resultArrayList, this);
        mGridView.setAdapter(gridViewAdapter);

        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, 0);
            }
        });

    }

    @Override
    public void validateProductAnotherFailure(String message) {
        showCustomToast(message);
    }
}
