package com.example.retroex1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PostApi {

    @GET("posts")
    Call<List<Post>> getPosts(); //주소가 요청이되면 함수가 실행되서 List에 다운 Call(성공과 실패를 받을 수 있음)

    public static final Retrofit retrofit = new Retrofit.Builder()  // postApi가 뜰 때 메모리에 같이 뜬다.
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
