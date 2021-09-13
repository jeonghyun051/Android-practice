package com.example.ddangnmarket.src.Profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.Profile.interfaces.ProfileActivityView;
import com.example.ddangnmarket.src.Profile.models.RequestProfile;
import com.example.ddangnmarket.src.main.writing.WritingAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class Profile extends BaseActivity implements ProfileActivityView {
    ImageView mIvProfile;
    TextView mTvSubmit;
    final int PICK_IMAGE_MULTIPLE = 1;
    ArrayList<Uri> mUriArrayList = new ArrayList<>();
    FirebaseStorage mStorage = FirebaseStorage.getInstance();
    StorageReference mStorageReference = mStorage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        mIvProfile = findViewById(R.id.profile_iv_profile_image);
        mTvSubmit = findViewById(R.id.profile_btn_submit);

        mIvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUrl();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void change(Uri url) {
        ProfileService profileService = new ProfileService(this);
        RequestProfile requestProfile = new RequestProfile();
        requestProfile.setProfileUrl(url.toString());
        profileService.patchProfile(requestProfile);
    }

    @Override
    public void validateProfileSuccess(boolean isSuccess, int code, String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProfileFailure() {
    }

    public void imageUrl() {
        for (int i = 0; i < mUriArrayList.size(); i++) {
            StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());

            final int finalInt = i;
            ref.putFile(mUriArrayList.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            change(uri);
                        }
                    });
                }
            });
        }
    }

    public void uploadImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

        System.out.println("사진업로드");
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
                            mIvProfile.setImageURI(uri);
                            mIvProfile.setBackground(new ShapeDrawable(new OvalShape()));
                            mIvProfile.setClipToOutline(true);
                            mIvProfile.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
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
}
