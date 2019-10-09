package com.tharun.socialcop.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tharun.socialcop.Components.CurrentPost;
import com.tharun.socialcop.Interfaces.MediaApi;
import com.tharun.socialcop.Interfaces.PostApi;
import com.tharun.socialcop.Models.Category;
import com.tharun.socialcop.Models.Field;
import com.tharun.socialcop.Models.Media;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.Models.Signed;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {

    ViewPager postViewPager;
    FragmentPagerAdapter postPagerAdapter;

    Button chooseCategoryButton;
    ImageView previewImage;
    EditText enterDescription;
    ImageButton openCameraButton;

    Button submitPost;


    String prntid;
    String type = "post";

    LinearLayout parentPostLayout;

    Bitmap bitmap;

    ProgressBar postUploadProgressBar;


    public static Category category;

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance(PostActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().hide();


        if(getIntent().getStringExtra("parentid")!=null){
            prntid = getIntent().getStringExtra("parentid");
            type = getIntent().getStringExtra("type");
        }

        parentPostLayout = findViewById(R.id.parentpostlayoutid);

        chooseCategoryButton = findViewById(R.id.chosecategoryid);
        previewImage = findViewById(R.id.previewimageid);
        enterDescription = findViewById(R.id.enterdescriptionid);
        openCameraButton = findViewById(R.id.opencamerabuttonid);

        postUploadProgressBar = findViewById(R.id.postuploadprogressbarid);

        submitPost = findViewById(R.id.submitpostid);


        if(prntid!=null){
            chooseCategoryButton.setVisibility(View.GONE);
            postUploadProgressBar.setVisibility(View.VISIBLE);
        }

        if(prntid!=null) {
            Call<Post> postCall = retrofit.create(PostApi.class).getPost(prntid);


            postCall.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    postUploadProgressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        CurrentPost parentPost = new CurrentPost(PostActivity.this);
                        parentPost.setPost(response.body());
                        parentPostLayout.addView(parentPost);
                    } else {
                        Toast.makeText(PostActivity.this, "Something Went Wrnong", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(PostActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }



        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(PostActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(PostActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PostActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }else{
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                }

            }
        });


        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postUploadProgressBar.setVisibility(View.VISIBLE);

                if(bitmap!=null){


                    final MediaApi mediaApi = retrofit.create(MediaApi.class);

                    final File file = saveBitmap(bitmap);


                        Call<Signed> signedCall = mediaApi.getSignedUpload(file.getName());
                        signedCall.enqueue(new Callback<Signed>() {
                            @Override
                            public void onResponse(Call<Signed> call, Response<Signed> response) {

                                if(response.isSuccessful()){

                                    final String image = response.body().getId();
                                    Field field = response.body().getFields();



                                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*0"),file);
                                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

                                    Call<String> mediaCall = mediaApi.uploadMedia(response.body().getUrl(),getRequestBody(field.getKey()),getRequestBody(field.getContentDisposition()),getRequestBody(field.getContentType()),getRequestBody(field.getBucket()),getRequestBody(field.getXAMZAlgorithm()),getRequestBody(field.getXAMZCredentials()),getRequestBody(field.getXAMZDate()),getRequestBody(field.getPolicy()),getRequestBody(field.getXAMZSignature()),filePart);


                                    mediaCall.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if(response.isSuccessful()){

                                                Post post = new Post(prntid,enterDescription.getText().toString(),null,null,image,type);

                                                uploadPost(post);

                                            }else{

                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });




                                }else{

                                }


                            }

                            @Override
                            public void onFailure(Call<Signed> call, Throwable t) {

                            }
                        });





                }else{

                        uploadPost(new Post(prntid,enterDescription.getText().toString(),"awd","Mumbai",null,type));

                }






            }
        });




        //postViewPager = findViewById(R.id.postviewpagerid);

//        postPagerAdapter = new WritePagerAdapter(getSupportFragmentManager());
//        postViewPager.setAdapter(postPagerAdapter);


    }




    public void uploadPost(Post post){

        PostApi postApi = retrofit.create(PostApi.class);

        Call<ResponseBody> postCall = postApi.putPost(post);

        postCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                    Toast.makeText(PostActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    postUploadProgressBar.setVisibility(View.GONE);

                }else{

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        postUploadProgressBar.setVisibility(View.INVISIBLE);


    }


    public void setPosition(int position){
        postViewPager.setCurrentItem(position);
        postPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0 :
                if(grantResults.length>1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);


                }else{
                    startActivity(new Intent(PostActivity.this, HomeActivity.class));
                }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0  && resultCode == Activity.RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
            previewImage.setImageBitmap(bitmap);
        }

    }

    private RequestBody getRequestBody(String value){
        return RequestBody.create(MediaType.parse("multipart/form-data"),value);

    }


    private File saveBitmap(Bitmap bitmap){


        File file = new File(getCacheDir(),"xyz.jpeg");

        try {

            file.createNewFile();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

            byte[] bitmapBytes = byteArrayOutputStream.toByteArray();

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(bitmapBytes);
            fileOutputStream.flush();
            fileOutputStream.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

}
