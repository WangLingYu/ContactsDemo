package com.example.wang.mynotedemo.api;

import com.example.wang.mynotedemo.model.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wang on 16/8/11.
 */
public interface Api {
    @GET("index")
    Call<Person> repo();

}
