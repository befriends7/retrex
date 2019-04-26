package com.jeugene.retrex;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIcreate {

    private static final String BASE_URL = "https://api.nytimes.com/";
    private static Retrofit retrofit = null;

    public APIcreate() {



    }

    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                       .addConverterFactory(GsonConverterFactory.create())
                       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                       .baseUrl(BASE_URL)
                       .build();

        }

        return retrofit;

    }

}
