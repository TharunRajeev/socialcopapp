package com.tharun.socialcop.Fragments;



import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tharun.socialcop.R;


public class ForgotPasswordFragment extends Fragment {

    private static final String emailAddParam = "email";

    // TODO: Rename and change types of parameters
    private String emailAdd;

    private OnFragmentInteractionListener mListener;
    private TextInputLayout emailLayout;
    private TextInputEditText email;
    private Button confirm ;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance(String param1) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        Bundle args = new Bundle();
        args.putString(emailAddParam, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            emailAdd = getArguments().getString(emailAddParam,"");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view , savedInstanceState);

        confirm = view.findViewById(R.id.confirm);
        email = view.findViewById(R.id.emailET);
        emailLayout = view.findViewById(R.id.email);
        if(!TextUtils.isEmpty(emailAdd))
            email.setText(emailAdd);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmailValid(email.getText().toString())){
                    mListener.onConfirm(email.getText().toString());
                }
                else {
                    emailLayout.setError("Invalid Email Address");
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean isEmailValid(String emailDetail){
        return(android.util.Patterns.EMAIL_ADDRESS.matcher(emailDetail).matches());
    }

    public interface OnFragmentInteractionListener {
        void onConfirm(String x);
    }
}
