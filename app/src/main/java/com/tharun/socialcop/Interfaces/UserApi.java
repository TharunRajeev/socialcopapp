package com.tharun.socialcop.Interfaces;

import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/user")
    Call<User> getUser(@Query("username") String username);

    @POST("/auth/login")
    Call<ResponseBody> checkUser(@Body User user);

    @POST("/auth/register")
    Call<ResponseBody> newUser(@Body User user);

    @GET("/auth/verification_code")
    Call<ResponseBody> getVerificationCode(@Query("email") String email);



    @POST("forgetpassword/")
    @FormUrlEncoded
    Call<ResponseBody>forgetPassword(@Field("email") String email);


}
