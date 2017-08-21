package com.eaglesofttech.tmdbassignment.network;

import android.support.compat.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 19/8/17.
 */

public class ApiClient {
    private static Retrofit retrofit;
    public static String Base_Url = "https://api.themoviedb.org/3/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG)
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.SECONDS)
                    .connectTimeout(0, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
