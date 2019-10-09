package com.tharun.socialcop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tharun.socialcop.Adapters.FeedsRecyclerViewAdapter;
import com.tharun.socialcop.Components.CurrentPost;
import com.tharun.socialcop.Interfaces.PostApi;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailedPostActivity extends AppCompatActivity {

    LinearLayout currentPostLayout;

    TextView requestsHeaderTextView;
    RecyclerView requestsRecyclerView;
    TextView commentsHeaderTextView;
    RecyclerView commentsRecyclerView;

    String pid;




    private Retrofit retrofit;


    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        if(getIntent()!=null){
            pid = getIntent().getStringExtra("pid");
        }


        currentPostLayout = findViewById(R.id.currentpostid);


        requestsHeaderTextView = findViewById(R.id.requestsheadertextviewid);
        requestsRecyclerView = findViewById(R.id.postrequestsrecyclerviewid);
        requestsRecyclerView.setHasFixedSize(true);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        commentsRecyclerView = findViewById(R.id.postcommentsrecyclerviewid);
        commentsRecyclerView.setHasFixedSize(true);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadingProgressBar = findViewById(R.id.detailedpostloadingprogressbarid);

        retrofit = RetrofitInstance.getRetrofitInstance(this);

        PostApi postApi = retrofit.create(PostApi.class);

        Call<Post> postCall = postApi.getPost(pid);

        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    loadingProgressBar.setVisibility(View.GONE);

                    Post post = response.body();
                    CurrentPost currentPost = new CurrentPost(DetailedPostActivity.this);
                    currentPost.setPost(post);
                    currentPostLayout.addView(currentPost);

                    if(post.getType().equals("post")){
                        requestsHeaderTextView.setVisibility(View.VISIBLE);
                    }else{
                        requestsHeaderTextView.setVisibility(View.GONE);
                    }

                }else{
                    Toast.makeText(DetailedPostActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(DetailedPostActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Post>> requestsCall = postApi.getPosts(pid,null,"request",null,null);

        requestsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    loadingProgressBar.setVisibility(View.GONE);

                    Log.d("Requests",response.body().get(0).getDescription());

                    FeedsRecyclerViewAdapter requestsRecyclerAdapter = new FeedsRecyclerViewAdapter(DetailedPostActivity.this,response.body());
                    requestsRecyclerView.setAdapter(requestsRecyclerAdapter);

                }else{

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


        Call<List<Post>> commentsCall = postApi.getPosts(pid,null,"comment",null,null);

        commentsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){

                    loadingProgressBar.setVisibility(View.GONE);
                    FeedsRecyclerViewAdapter commentsRecyclerAdapter = new FeedsRecyclerViewAdapter(DetailedPostActivity.this,response.body());
                    commentsRecyclerView.setAdapter(commentsRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
}
