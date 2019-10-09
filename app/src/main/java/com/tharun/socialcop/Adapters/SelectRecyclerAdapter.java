package com.tharun.socialcop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tharun.socialcop.Activities.PostActivity;
import com.tharun.socialcop.Models.Category;
import com.tharun.socialcop.R;

import java.util.List;

public class SelectRecyclerAdapter extends RecyclerView.Adapter<SelectRecyclerAdapter.SelectRecyclerViewHolder>{

    private Context context;
    private List<Category> categoryList;


    public SelectRecyclerAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public SelectRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card,parent,false);
        return new SelectRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectRecyclerViewHolder holder, int position) {
        //Toast.makeText(context, "yo", Toast.LENGTH_SHORT).show();
        final Category category = categoryList.get(position);
        holder.categoryTextView.setText(category.getTitle());
        Glide.with(context).load(category.getImage()).centerCrop().into(holder.categoryImageView);

        holder.categoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PostActivity.category = category;

                ((PostActivity) context).setPosition(2);

            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }




    class SelectRecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImageView;
        TextView categoryTextView;

        SelectRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageView =  itemView.findViewById(R.id.categoryimageviewid);
            categoryTextView = itemView.findViewById(R.id.categorytitleid);


        }

    }
}
