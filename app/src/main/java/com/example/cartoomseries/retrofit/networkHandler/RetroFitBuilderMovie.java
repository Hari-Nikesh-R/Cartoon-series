package com.example.cartoomseries.retrofit.networkHandler;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitBuilderMovie {
    private static Retrofit instance;
    private RetroFitBuilderMovie()
    {}

    public static Retrofit getInstance()
    {
        if(instance==null){
            synchronized (RetroFitBuilderMovie.class){
                if(instance==null)
                {
                    instance =new Retrofit.Builder().baseUrl("https://www.omdbapi.com/").addConverterFactory(GsonConverterFactory.create()).client((new OkHttpClient())).build();
                }
            }
        }
        return instance;
    }


}
