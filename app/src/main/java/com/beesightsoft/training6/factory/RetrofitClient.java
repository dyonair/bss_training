package com.beesightsoft.training6.factory;

/**
 * Created by Dy Dy on 09/04/18.
 */

import com.beesightsoft.training6.service.model.RestPostService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RestPostService getAPIService(Retrofit retrofit) {
        return retrofit.create(RestPostService.class);
    }

}

