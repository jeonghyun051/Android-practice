package com.example.mvvmex1.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmex1.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends ViewModel {

    // LiveData, MutableLiveData(변하지않는 데이터)
    private MutableLiveData<List<Post>> mtPosts = new MutableLiveData<>();
    private static final String TAG = "PostViewModel";

    public MutableLiveData<List<Post>> 구독(){
        return mtPosts;
    }

    // 1
    public void 포스트한건추가(Post post){
        List<Post> posts = mtPosts.getValue();
        posts.add(post);
        mtPosts.setValue(posts);
        Log.d(TAG, "포스트한건추가: " + posts);

    }

    // 2
    public void 포스트변경(){
        List<Post> posts = mtPosts.getValue();
        posts.get(0).setTitle("테스트");
        mtPosts.setValue(posts);
        Log.d(TAG, "포스트변경: " + posts);
    }

    public void 데이터초기화(){
        List<Post> posts = new ArrayList<>();
        mtPosts.setValue(posts);
    }
}
