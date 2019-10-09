package com.tharun.socialcop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tharun.socialcop.Activities.DetailedPostActivity;
import com.tharun.socialcop.Components.CurrentPost;
import com.tharun.socialcop.Models.Post;
import com.tharun.socialcop.R;

import org.w3c.dom.Text;

import java.util.List;

public class FeedsRecyclerViewAdapter extends RecyclerView.Adapter<FeedsRecyclerViewAdapter.FeedsRecyclerViewHolder> {


    private Context context;
    private List<Post> postList;

    public FeedsRecyclerViewAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public FeedsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new CurrentPost(context);

        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        return new FeedsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsRecyclerViewHolder holder, int position) {
        final Post post = postList.get(position);


        holder.currentPost.setPost(post);

        holder.currentPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailedPostIntent = new Intent(context, DetailedPostActivity.class);

                detailedPostIntent.putExtra("pid",post.getPid());

                context.startActivity(detailedPostIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class FeedsRecyclerViewHolder extends RecyclerView.ViewHolder{

        private CurrentPost currentPost;

        FeedsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            currentPost = (CurrentPost) itemView;


        }
    }
}
