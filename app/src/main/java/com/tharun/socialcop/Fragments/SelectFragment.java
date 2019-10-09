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

        SelectRecyclerAdapter selectRecyclerAdapter = new SelectRecyclerAdapter(context,categoryList);
        selectRecyclerView.setAdapter(selectRecyclerAdapter);





    }
}
