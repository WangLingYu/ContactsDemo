package com.example.wang.mynotedemo.api;

import com.example.wang.mynotedemo.model.Person;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wang on 16/8/11.
 */
public interface Api {
    @GET("index/")
    Call<String> getPersons();

}
