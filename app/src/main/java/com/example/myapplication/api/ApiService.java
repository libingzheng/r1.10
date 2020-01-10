package com.example.myapplication.api;

import com.example.myapplication.entity.CarEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/shop/shopcart.json")
    Observable<CarEntity>getCartResponse();
}
