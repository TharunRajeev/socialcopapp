package com.tharun.socialcop.Activities;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tharun.socialcop.Fragments.ChangePasswordFragment;
import com.tharun.socialcop.Fragments.ForgotPasswordFragment;
import com.tharun.socialcop.Fragments.LoginFragment;
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

public class LoginActivity extends AppCompatActivity{

    FrameLayout frameLayout;
    ConstraintLayout loginActivity;
    Retrofit retrofit;
    UserApi service;
    SharedPreferences.Editor editor;


    FragmentManager fragmentManager;
    ImageButton backButton;

    private static final int RC_SIGN_IN=1;

    SharedPreferences userPrefs;
    SharedPreferences.Editor editUserPrefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivity = findViewById(R.id.loginActivity);
        backButton = findViewById(R.id.backArrow);

        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.frameLayout);

        fragmentManager.beginTransaction().replace(R.id.frameLayout,new LoginFragment()).commit();

        retrofit = RetrofitInstance.getRetrofitInstance(this);
        service = retrofit.create(UserApi.class);

        SharedPreferences prefs = this.getSharedPreferences("LogIn",MODE_PRIVATE);
        editor = prefs.edit();

        userPrefs = this.getSharedPreferences("UserDetails",MODE_PRIVATE);
        editUserPrefs = userPrefs.edit();




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

//    @Override
//    public void onLogin(String email,String password) {
//        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Snackbar.make(loginActivity, "Please enter credentials first! ", Snackbar.LENGTH_LONG).show();
//        } else {
//            Call<ResponseBody> call = service.checkUser(new User(email,password));
//            call.enqueue(new Callback<ResponseBody>() {
//
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> ResponseBodyonse) {
//                    if (ResponseBodyonse.body() != null) {
//                        if (ResponseBodyonse.body().getMsg().equals("Passwords match")){
//                            editor.putBoolean("signedIn",true).apply();
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                            finish();
//                        }
//                        else if (ResponseBodyonse.body().getMsg().equals("Passwords do not match"))
//                            Snackbar.make(loginActivity, "Email and Password doesn't match", Snackbar.LENGTH_LONG).show();
//                        else
//                            Snackbar.make(loginActivity, "Email and Password doesn't match", Snackbar.LENGTH_LONG).show();
//                    }else
//                        Snackbar.make(loginActivity, "There's problem with our servers, Please try again later!", Snackbar.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Snackbar.make(loginActivity, "Please make sure you're connected to internet", Snackbar.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    @Override
//    public void gotoSignUp(String email) {
//        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
//        intent.putExtra("email",email);
//        startActivity(intent);
//    }
//
//    @Override
//    public void forgetpassword(String email) {
//        ForgotPasswordFragment fragment = ForgotPasswordFragment.newInstance(email);
//        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).addToBackStack(null).commit();
//    }
//
//    @Override
//    public void SignInWithGoogle() {
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//
//            //Getting the GoogleSignIn Task
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                //Google Sign In was successful
//                account = task.getResult(ApiException.class);
//                editUserPrefs.putString("personName",account.getDisplayName());
//                editUserPrefs.putString("personEmail",account.getEmail());
//                editUserPrefs.putString("personPhoto",account.getPhotoUrl().toString());
//                editUserPrefs.putInt("radcoins",0);
//                editUserPrefs.apply();
//                Toast.makeText(LoginActivity.this,"Welcome "+ userPrefs.getString("personName","User"),Toast.LENGTH_SHORT).show();
//                SignUpFragment signUpFragment = SignUpFragment.newInstance(account.getEmail(),userPrefs.getString("personName","User").trim().split("\\s+")[0],userPrefs.getString("personName","User").trim().split("\\s+")[1],account.getIdToken());
//                fragmentManager.beginTransaction().replace(R.id.frameLayout,signUpFragment).addToBackStack(null).commit();
//                mGoogleSignInClient.signOut();
//            } catch (ApiException e) {
//                Toast.makeText(LoginActivity.this,"Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
    }


//    @Override
//    public void onFragmentInteraction() {
//
//    }
//


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragmentManager.popBackStack();

        try{



        }catch(Exception e){}
    }
}
