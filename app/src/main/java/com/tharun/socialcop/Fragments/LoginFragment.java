package com.tharun.socialcop.Fragments;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tharun.socialcop.Activities.HomeActivity;
import com.tharun.socialcop.Activities.SignupActivity;
import com.tharun.socialcop.Interfaces.UserApi;
import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginFragment extends Fragment {



    private Button login;
    private Button signUp;
    private Context context;
    private EditText email ;
    private EditText password ;
    private TextView forgetpassword ;
    private Button SignUpWithGoogle;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        login = view.findViewById(R.id.loginButton);
        signUp = view.findViewById(R.id.signUpButton);
        email = view.findViewById(R.id.email);
        password  = view.findViewById(R.id.password);
        forgetpassword = view.findViewById(R.id.forgetpassword);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);

                Call<ResponseBody> loginCall = retrofit.create(UserApi.class).checkUser(new User(email.getText().toString(),password.getText().toString()));

                loginCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){

                            SharedPreferences sharedPreferences = context.getSharedPreferences("DEFAULT_PREFERENCES",Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putBoolean("isLoggedIn",true);
                            editor.commit();


                            if((response.body()!=null)){
                                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            startActivity(new Intent(context, HomeActivity.class));
                            getActivity().finish();

                        }else{
                            if((response.body()!=null)){
                                Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, SignupActivity.class));
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    @Override
    public void onDetach() {
        super.onDetach();
        password.setText("");

    }



}
