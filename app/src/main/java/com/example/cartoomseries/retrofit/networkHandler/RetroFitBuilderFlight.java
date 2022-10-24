package com.example.cartoomseries.retrofit.networkHandler;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitBuilderFlight {
    private static Retrofit instance;
    private RetroFitBuilderFlight()
    {}

    public static Retrofit getInstance()
    {
        if(instance==null){
            synchronized (RetroFitBuilderFlight.class){
                if(instance==null)
                {
                    instance =new Retrofit.Builder().baseUrl("https://api.instantwebtools.net/").addConverterFactory(GsonConverterFactory.create()).client((new OkHttpClient())).build();
                }
            }
        }
        return instance;
    }


}
