package com.tharun.socialcop.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tharun.socialcop.Adapters.FeedsRecyclerViewAdapter;
import com.tharun.socialcop.Interfaces.PostApi;
import com.tharun.socialcop.Models.Media;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView feedRecyclerView;
    private Context context;
    private List<Post> postList;

    private ProgressBar postloadingProgressBar;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();




        feedRecyclerView  = view.findViewById(R.id.feedrecyclerviewid);



        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        postloadingProgressBar = view.findViewById(R.id.postsloadingprogessid);


        Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);

        PostApi postApi = retrofit.create(PostApi.class);

        Call<List<Post>> call = postApi.getPosts(null,null,null,null,null);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){

                    postloadingProgressBar.setVisibility(View.GONE);
                    postList = response.body();



                    feedRecyclerView.setAdapter(new FeedsRecyclerViewAdapter(context,postList));

                }else{
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "Something Went Wrong2", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
