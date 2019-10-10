package com.tharun.socialcop.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;


import com.google.android.material.snackbar.Snackbar;
import com.tharun.socialcop.Fragments.SignupFragment;
import com.tharun.socialcop.Fragments.VerifyEmailFragment;
import com.tharun.socialcop.Interfaces.UserApi;
import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity{

    FrameLayout frameLayout;
    SharedPreferences userPrefs ;
    SharedPreferences.Editor editSserPrefs;
    ConstraintLayout signUpActivity;
    ImageButton backButton;


    public static User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String emailDetails = intent.getStringExtra("email");
        setContentView(R.layout.activity_signup);
        RetrofitInstance retrofitInstance = new RetrofitInstance();


        frameLayout = findViewById(R.id.frameLayout);
        userPrefs = this.getSharedPreferences("LogIn",MODE_PRIVATE);
        editSserPrefs = userPrefs.edit();
        signUpActivity = findViewById(R.id.signUpActivity);
        backButton = findViewById(R.id.backArrow);

        SignupFragment signUpFragment = SignupFragment.newInstance(emailDetails);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,signUpFragment).commit();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}

