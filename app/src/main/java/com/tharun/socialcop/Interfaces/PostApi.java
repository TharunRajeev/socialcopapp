package com.tharun.socialcop.Interfaces;

import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.Models.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostApi {

    @GET("/post/posts")
    Call<List<Post>> getPosts(@Query("parentid") String prntid, @Query("uid") String quid , @Query("type") String type , @Query("status") String status , @Query("accepted") String accepted);

    @GET("/post")
    Call<Post> getPost(@Query("pid") String pid);


    @GET("/post/upvote")
    Call<ResponseBody> upvote(@Query("pid") String pid);

    @GET("/post/plusone")
    Call<ResponseBody> plusone(@Query("pid") String pid);

    @GET("/post/resolve")
    Call<ResponseBody> resolve(@Query("pid") String pid , @Query("parentid") String parentid);

    @POST("/post")
    Call<ResponseBody> putPost(@Body Post post);

    @GET("/post/delete")
    Call<ResponseBody> deletePost(@Query("pid") String pid);

}
