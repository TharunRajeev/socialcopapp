package com.tharun.socialcop.Interfaces;

import com.tharun.socialcop.Models.Field;
import com.tharun.socialcop.Models.Signed;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MediaApi {

    @GET("/upload")
    Call<Signed> getSignedUpload(@Query("filename") String filename);

    @Multipart
    @POST
    Call<String> uploadMedia(@Url String url, @Part("key") RequestBody key, @Part("Content-Disposition") RequestBody contentDisposition, @Part("Content-Type") RequestBody contentType, @Part("bucket") RequestBody bucket, @Part("X-Amz-Algorithm") RequestBody XAMZAlgorithm, @Part("X-Amz-Credential") RequestBody XAMZCredentials, @Part("X-Amz-Date") RequestBody XAMZDate , @Part("Policy") RequestBody Policy, @Part("X-Amz-Signature")  RequestBody XAMZSignature, @Part MultipartBody.Part file);
}
