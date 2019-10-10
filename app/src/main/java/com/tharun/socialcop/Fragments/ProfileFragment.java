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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tharun.socialcop.Adapters.FeedsRecyclerViewAdapter;
import com.tharun.socialcop.Interfaces.PostApi;
import com.tharun.socialcop.Interfaces.UserApi;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Context context;

    ImageView userProfileImageView;
    TextView userProfileTextView;


    ProgressBar userPostsProgessBar;

    RecyclerView userpostsRecyclerView;

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();


        userProfileImageView = view.findViewById(R.id.userprofileimageid);
        userProfileTextView = view.findViewById(R.id.userprofilenameid);

        userpostsRecyclerView = view.findViewById(R.id.userpostrecyclerviewid);
        userPostsProgessBar = view.findViewById(R.id.userpostsprogressbarid);

        userpostsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        userpostsRecyclerView.setHasFixedSize(true);

        Call<User> userCall = retrofit.create(UserApi.class).getUser("tharunrajeev");

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    Glide.with(context).load(response.body().getProfile()).into(userProfileImageView);
                    userProfileTextView.setText(response.body().getFirstname());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Call<List<Post>> userPosts = retrofit.create(PostApi.class).getPosts(null,"tharun",null,null,null);

        userPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                userPostsProgessBar.setVisibility(View.GONE);

                if(response.isSuccessful()){

                    FeedsRecyclerViewAdapter userPostsRecyclerAdapter = new FeedsRecyclerViewAdapter(context,response.body());
                    userpostsRecyclerView.setAdapter(userPostsRecyclerAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


    }
}
