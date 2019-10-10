package com.tharun.socialcop.Fragments;



import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tharun.socialcop.Activities.SignupActivity;
import com.tharun.socialcop.Interfaces.UserApi;
import com.tharun.socialcop.Models.ResponseBody;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.NetworkHandling.RetrofitInstance;
import com.tharun.socialcop.R;
import com.tharun.socialcop.UIHelper.OTPEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class VerifyEmailFragment extends Fragment {


    private Button verify ,timer;
    private OTPEditText otp;
    private TextView error;
    private String email;


    Context context;

    public VerifyEmailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        getOtp(SignupActivity.user.getEmail());

        verify = view.findViewById(R.id.verifyBtn);
        timer = view.findViewById(R.id.timer);
        otp = view.findViewById(R.id.otpET);
        error = view.findViewById(R.id.errorView);

        timer.setEnabled(false);
        verify.setEnabled(false);
        verify.setVisibility(View.INVISIBLE);


        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(TextUtils.isEmpty(s) || s.length() < 6){
                    error.setText("Enter The OTP sent to the provided Email Address ");

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        new CountDownTimer(180000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                error.setText("OOPS Times UP , Hit Retry ");
                verify.setEnabled(false);
                timer.setText("RETRY");
                timer.setEnabled(true);
            }
        }.start();

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo retry api call for sending email
                //mListener.retryOnConfirm(email);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);
                UserApi service = retrofit.create(UserApi.class);

                SignupActivity.user.setVerificationCode(Integer.parseInt(otp.getText().toString()));

                Call<ResponseBody> call = service.newUser(SignupActivity.user);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> Response) {
                        if (Response.isSuccessful()) {


                                //todo api call for sending otp it shud automatically send seeing the authcreds null duh

                            if((Response.body()!=null)){
                                Toast.makeText(context, Response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }



                        }else {
                            if((Response.body()!=null)){
                                Toast.makeText(context, Response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "Something went wrong ", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }


    private void getOtp(String email){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance(context);
        UserApi service = retrofit.create(UserApi.class);

        Call<ResponseBody> otpCall = service.getVerificationCode(email);

        otpCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                    verify.setVisibility(View.VISIBLE);
                    verify.setEnabled(true);
                    timer.setEnabled(true);
                    Toast.makeText(context, "Otp Sent", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


}
