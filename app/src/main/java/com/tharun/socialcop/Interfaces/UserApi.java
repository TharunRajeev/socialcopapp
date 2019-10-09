package com.tharun.socialcop.Interfaces;

import com.tharun.socialcop.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/user")
    Call<User> getUser(@Query("username") String username);

}
