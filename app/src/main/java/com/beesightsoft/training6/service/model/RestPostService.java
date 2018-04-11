package com.beesightsoft.training6.service.model;

/**
 * Created by Dy Dy on 09/04/18.
 */

import android.database.Observable;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestPostService {
    @POST("posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);

    @POST("posts")
    @FormUrlEncoded
    Observable<Post> savePostRxJava(@Field("title") String title,
                                    @Field("body") String body,
                                    @Field("userId") long userId);

    @PUT("posts/{id}")
    @FormUrlEncoded
    Call<Post> updatePost(@Path("id") long id,
                          @Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);

    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") long id);
}