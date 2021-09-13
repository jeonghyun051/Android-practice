package com.example.ddangnmarket.src.main.writing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.main.writing.interfaces.WritingActivityView;
import com.example.ddangnmarket.src.main.writing.models.RequestProduct;
import com.example.ddangnmarket.src.main.writing.models.RequestProductImage;
import com.example.ddangnmarket.src.main.writing.models.Result;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class WritingActivity extends BaseActivity implements WritingActivityView {
    EditText mEtTitle, mEtPrice, mEtDescription;
    TextView mTvNumOfPic, mTvCategories, mTvCategoryNo;
    final int PICK_IMAGE_MULTIPLE = 1;
    ArrayList<Uri> mUriArrayList = new ArrayList<>();
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;
    RecyclerView mRecyclerView;
    WritingAdapter mWritingAdapter;
    int categoryNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        mEtTitle = findViewById(R.id.writing_et_title);
        mEtPrice = findViewById(R.id.writing_et_price);
        mEtDescription = findViewById(R.id.writing_et_description);
        mTvNumOfPic = findViewById(R.id.writing_tv_number_of_pic);
        mTvCategories = findViewById(R.id.writing_tv_categories);
        mTvCategoryNo = findViewById(R.id.writing_tv_categoryNo);
        mRecyclerView = findViewById(R.id.writing_rv);

    }

    public void writingOnClick(View view) {
        switch (view.getId()) {
            case R.id.writing_btn_back:
                onBackPressed();
                break;
            case R.id.writing_btn_submit:
                submit();
                break;
            case R.id.writing_btn_categories:
                showCategories();
                break;
            case R.id.writing_btn_upload:
                uploadImage();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void uploadImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

        System.out.println("사진업로드");
    }

    public void submit() {
        System.out.println("writingActivity submit : ");
        WritingService writingService = new WritingService(this);
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setTitle(mEtTitle.getText().toString());
        requestProduct.setPrice(Integer.parseInt(mEtPrice.getText().toString()));
        requestProduct.setText(mEtDescription.getText().toString());
        requestProduct.setCategoriesNo(Integer.parseInt(mTvCategoryNo.getText().toString()));   //수정요망
        writingService.postProduct(requestProduct);
    }

    public void showCategories() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WritingActivity.this);
        final String[] versionArray = new String[] {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
                "뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};

        builder.setItems(versionArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvCategories.setText(versionArray[which]);
                mTvCategoryNo.setText((which+1)+"");
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && data != null) {
                if (data.getData() != null) {
                    Uri uri;
                    //mUriArrayList.add(uri);

                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            uri = item.getUri();
                            System.out.println("uri : " + uri);
                            mUriArrayList.add(uri);
                        }
                        mTvNumOfPic.setText(mClipData.getItemCount() + "/10");
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        mWritingAdapter = new WritingAdapter(mUriArrayList, this);
                        mRecyclerView.setAdapter(mWritingAdapter);
                    }
                }
            } else {
                showCustomToast("You haven't picked Image");
            }
        } catch (Exception e) {
            showCustomToast("Something went wrong");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, final Result result) {
        if (code == 100) {
            showCustomToast(message);

            for (int i = 0; i < mUriArrayList.size(); i++) {
                StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());

                final int finalInt = i;
                ref.putFile(mUriArrayList.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                postProductImage(uri, finalInt + 1, result.getProductNo().get(0).getProductNo());
                            }
                        });
                    }
                });
            }
        } else if (code == 200) showCustomToast(message);
        else if (code == 201) showCustomToast(message);
        else if (code == 202) showCustomToast(message);

    }

    public void postProductImage(Uri downloadURL, int index, int productNo) {
        System.out.println(downloadURL + " " + index + " " + productNo);
        WritingService writingService = new WritingService(this);
        RequestProductImage requestProductImage = new RequestProductImage();
        requestProductImage.setImageUrl(downloadURL.toString());
        requestProductImage.setImageIndex(index);
        requestProductImage.setProductNo(productNo);
        writingService.postProductImage(requestProductImage, index);
    }

    @Override
    public void validateProductFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProductImageSuccess(boolean isSuccess, int code, String message, int index) {
        //showCustomToast(message);

        if (isSuccess && mUriArrayList.size() == index) {
            showCustomToast(message);
            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateProductImageFailure(String message) {
        showCustomToast(message);
    }
}
