package com.rep.organiza.organizarep.api;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rep.organiza.organizarep.BuildConfig;
import com.rep.organiza.organizarep.model.Test;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class OrganizaRepAPI {

    private static OrganizaRepService organizaRepAPI;

    public interface OrganizaRepService {

        @GET("api/test")
        Call<Test> test(@Header("Test") String testName);

    }

    public static OrganizaRepService get() {
        OkHttpClient.Builder builder = createBuilder();
        OkHttpClient client = builder.build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        if (organizaRepAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.ENDPOINT)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            organizaRepAPI = retrofit.create(OrganizaRepService.class);
        }
        return organizaRepAPI;
    }

    @NonNull
    private static OkHttpClient.Builder createBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
    }
}