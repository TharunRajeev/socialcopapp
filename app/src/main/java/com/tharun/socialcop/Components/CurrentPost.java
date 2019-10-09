package com.tharun.socialcop.Components;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.tharun.socialcop.Activities.PostActivity;
import com.tharun.socialcop.Interfaces.PostApi;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CurrentPost extends CardView {

    Context context;

    Post post;


    TextView usernameTextView;
    ImageView userProfileImageView;
    TextView locationTextView;
    ImageView mediaImageView;
    TextView tagTextView;
    TextView descriptionTextView;
    Button postacceptButton;
    ImageButton upvoteButton;
    TextView upvotesCount;
    ImageButton plusoneButton;
    TextView plusonesCount;
    ImageButton shareButton;
    ImageButton commentButton;

    TextView createdAtText;

    Button postrequestButton;

    public CurrentPost(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    private void initView(){
        View itemView  = inflate(context,R.layout.post_card,this);

        usernameTextView = itemView.findViewById(R.id.postusernameid);
        userProfileImageView = itemView.findViewById(R.id.postuserprofileid);
        locationTextView = itemView.findViewById(R.id.postlocationid);
        mediaImageView = itemView.findViewById(R.id.postmediaimageviewid);
        tagTextView = itemView.findViewById(R.id.posttagid);
        descriptionTextView = itemView.findViewById(R.id.postdescriptionid);
        postacceptButton = itemView.findViewById(R.id.postacceptid);
        upvoteButton = itemView.findViewById(R.id.postupvoteid);
        upvotesCount = itemView.findViewById(R.id.upvotescountid);
        plusoneButton = itemView.findViewById(R.id.postplusoneid);
        plusonesCount = itemView.findViewById(R.id.plusonescountid);
        commentButton = itemView.findViewById(R.id.postcommentid);
        postrequestButton = itemView.findViewById(R.id.postrequestbuttonid);

        createdAtText = itemView.findViewById(R.id.createdattextid);

        usernameTextView.setText(post.getUser().getName());
        Glide.with(context).load(post.getUser().getProfile()).into(userProfileImageView);
        locationTextView.setText(post.getLocation());
        Glide.with(context).load(post.getImage()).into(mediaImageView);
        tagTextView.setText(post.getCategory());
        descriptionTextView.setText(post.getDescription());
        upvotesCount.setText(Integer.toString(post.getUpvotes()));
        plusonesCount.setText(Integer.toString(post.getPlusones()));
        createdAtText.setText(new SimpleDateFormat("dd/MM/YYYY").format(new Date(post.getCreatedAt())));


        if(post.getParentid()!=null){
            postrequestButton.setEnabled(false);
            postrequestButton.setVisibility(GONE);

            if(post.getType().equals("request")){
                postacceptButton.setVisibility(VISIBLE);
            }
        }



        if(post.isUpvoted()) {
            upvoteButton.setImageResource(R.drawable.ic_upvote_red);

        }

        if(post.isPlusoned()){
            plusoneButton.setImageResource(R.drawable.ic_plus_one_red);
        }



        final Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);

        upvoteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                int upvotes;

                if (!post.isUpvoted()){
                    upvoteButton.setImageResource(R.drawable.ic_upvote_red);
                    post.setUpvoted(true);
                    upvotes = post.getUpvotes()+1;
                    upvotesCount.setText(Integer.toString(upvotes));

                }else{
                    upvoteButton.setImageResource(R.drawable.upvote);
                    post.setUpvoted(false);
                    upvotes = post.getUpvotes()-1;
                    upvotesCount.setText(Integer.toString(upvotes));

                }

                post.setUpvotes(upvotes);


                PostApi postApi = retrofit.create(PostApi.class);



                Call<ResponseBody> upvoteCall = postApi.upvote(post.getPid());
                upvoteCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });


        plusoneButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                int plusones;

                if (!post.isPlusoned()){
                    plusoneButton.setImageResource(R.drawable.ic_plus_one_red);
                    post.setPlusoned(true);
                    plusones = post.getPlusones()+1;
                    plusonesCount.setText(Integer.toString(plusones));
                }else{
                    plusoneButton.setImageResource(R.drawable.ic_plus_one_black);
                    post.setPlusoned(false);
                    plusones = post.getPlusones()-1;
                    plusonesCount.setText(Integer.toString(plusones));
                }


                post.setPlusones(plusones);

                final PostApi postApi = retrofit.create(PostApi.class);

                Call<ResponseBody> plusoneCall = postApi.plusone(post.getPid());

                plusoneCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){

                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });


        commentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postRequestIntent = new Intent(context, PostActivity.class);
                postRequestIntent.putExtra("parentid",post.getPid());
                postRequestIntent.putExtra("type","comment");
                context.startActivity(postRequestIntent);
            }
        });

        postrequestButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent postRequestIntent = new Intent(context, PostActivity.class);
                postRequestIntent.putExtra("parentid",post.getPid());
                postRequestIntent.putExtra("type","request");
                context.startActivity(postRequestIntent);

            }
        });


        postacceptButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> acceptCall = retrofit.create(PostApi.class).resolve(post.getPid(),post.getParentid());

                acceptCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Resolved", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });


    }


    public void setPost(Post post){
        this.post = post;

        initView();

    }


}
