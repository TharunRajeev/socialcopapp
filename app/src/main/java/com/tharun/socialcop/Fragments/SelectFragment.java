package com.tharun.socialcop.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tharun.socialcop.Adapters.SelectRecyclerAdapter;
import com.tharun.socialcop.Interfaces.SelectRecyclerInterface;
import com.tharun.socialcop.Models.Category;
import com.tharun.socialcop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends DialogFragment {

    private Context context;

    private RecyclerView selectRecyclerView;

    public SelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        selectRecyclerView = view.findViewById(R.id.selectrecyclerviewid);
        selectRecyclerView.setHasFixedSize(true);
        selectRecyclerView.setLayoutManager(new GridLayoutManager(context,2));

        List<Category> categoryList =  new ArrayList<>();

        categoryList.add(new Category("Faulty Signal","https://faraipyro.com/wp-content/uploads/2017/05/Traffic-Lights.jpg"));
        categoryList.add(new Category("Potholes","https://scx1.b-cdn.net/csz/news/800/2018/potholeshowe.jpg"));
        categoryList.add(new Category("Traffic Jam","https://compote.slate.com/images/31bf335d-92c5-452c-9181-71504defa8a3.jpg"));
        categoryList.add(new Category("Accident","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJOqWYCuTol0GAqVon63X1Xn7uPLXr9dwKXuh8it5T4EcqNEGc_A"));
        categoryList.add(new Category("Garbage On The Road","https://timesofindia.indiatimes.com/photo/63993534.cms"));
        categoryList.add(new Category("Injured Stray Animals","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRM4E522NNkJiSUX74rzK8ILjnL1w_sGgDpeaT7jcu3cL3nvLU_"));
        categoryList.add(new Category("Flood","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoqrW1o5uxH9OcEXtNsoyavT4p3MKpuswPpt2p-h7qeyqtzBBRdg"));
        categoryList.add(new Category("Sewages","https://s3.ap-southeast-1.amazonaws.com/images.deccanchronicle.com/dc-Cover-eg7jeas8couga12cojc91o5mj4-20180502010730.Medi.jpeg"));
        categoryList.add(new Category("Damaged Sign Board","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmS9RkCY5tXqeCQY21I1Wj7kpJLRD8HXjoSJMSyK1FbvaOdizXqg"));
        categoryList.add(new Category("Need For Anything","data:image/png;base64,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"));
        SelectRecyclerAdapter selectRecyclerAdapter = new SelectRecyclerAdapter(context,categoryList);
        selectRecyclerView.setAdapter(selectRecyclerAdapter);





    }
}
