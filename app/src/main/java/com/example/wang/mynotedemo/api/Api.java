package com.example.wang.mynotedemo.api;

import com.example.wang.mynotedemo.model.LoginResult;
import com.example.wang.mynotedemo.model.Person;
import com.example.wang.mynotedemo.ui.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wang on 16/8/11.
 */
public interface Api {
    @GET("{customer_id}/contacts")
    Call<List<Person>> getContacts(@Path("customer_id") String customer_id);

    @POST("login")
    Call<LoginResult> login(@Body LoginActivity.LoginRequest loginRequest);
}


