package com.example.myapplication.utils;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.myapplication.api.Api;
import com.example.myapplication.api.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private Retrofit retrofit;
    private RetrofitUtil(){
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (retrofitUtil==null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil==null){
                    retrofitUtil=new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }
    public boolean is(){
        return NetworkUtils.isConnected();
    }
    public <T> T createservice(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
