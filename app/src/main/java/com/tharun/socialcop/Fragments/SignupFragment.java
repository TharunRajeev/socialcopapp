package com.tharun.socialcop.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;

import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.tharun.socialcop.Activities.SignupActivity;
import com.tharun.socialcop.Models.User;
import com.tharun.socialcop.R;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignupFragment extends Fragment {


    private  EditText email,password,fName,lName,Cpassword;

    private EditText usernameEditText;

    private Button signUp;

    private TextInputLayout passwordLayout,CpasswordLayout,fNameLayout,lNameLayout,emailLayout;

    private String emailDetail,firstName,lastName,passcode,Cpasscode;

    private String username;

    private TextView termsLink;

    private String authCreds = "";

    private View focusView = null;


    Context context;


    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(String email) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString("email", email);
        fragment.setArguments(args);
        return fragment;
    }

    public static SignupFragment newInstance(String email,String fname,String lname,String authCreds) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString("email", email);
        args.putString("fname",fname);
        args.putString("lname",lname);
        args.putString("authCreds",authCreds);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        email = view.findViewById(R.id.emailAdd);
        password = view.findViewById(R.id.password);
        Cpassword = view.findViewById(R.id.confirm_password);
        fName = view.findViewById(R.id.firstName);
        lName = view.findViewById(R.id.lastName);
        signUp = view.findViewById(R.id.signUp);
        termsLink = view.findViewById(R.id.terms);
        CpasswordLayout = view.findViewById(R.id.CpasswordLayout);
        passwordLayout = view.findViewById(R.id.passwordLayout);
        fNameLayout = view.findViewById(R.id.fNameLayout);
        lNameLayout = view.findViewById(R.id.lNameLayout);
        emailLayout = view.findViewById(R.id.emailLayout);

        usernameEditText = view.findViewById(R.id.usernameAdd);

        email.addTextChangedListener(new CustomTextWatcher(email));
        password.addTextChangedListener(new CustomTextWatcher(password));
        Cpassword.addTextChangedListener(new CustomTextWatcher(Cpassword));
        fName.addTextChangedListener(new CustomTextWatcher(fName));
        lName.addTextChangedListener(new CustomTextWatcher(lName));





        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailLayout.setError(null);
                fNameLayout.setError(null);
                lNameLayout.setError(null);
                passwordLayout.setError(null);
                CpasswordLayout.setError(null);

                emailDetail = email.getText().toString();
                firstName = fName.getText().toString();
                lastName = lName.getText().toString();
                passcode = password.getText().toString();
                Cpasscode = Cpassword.getText().toString();
                username = usernameEditText.getText().toString();

                boolean cancel = false;




                    cancel  = checkPassword(passcode,Cpasscode);

                    if (TextUtils.isEmpty(emailDetail)) {
                        emailLayout.setError("Field Required");
                        focusView = email;
                        cancel = true;
                    } else if (!isEmailValid(emailDetail)) {
                        emailLayout.setError("Email Invalid");
                        focusView = email;
                        cancel = true;
                    }

                    if (TextUtils.isEmpty(lastName)) {
                        //lNameLayout.setError(getString(R.string.error_field_required));
                        focusView = lName;
                        cancel = true;
                    }

                    if (TextUtils.isEmpty(firstName)) {
                        //fNameLayout.setError(getString(R.string.error_field_required));
                        focusView = fName;
                        cancel = true;
                    }


                    if(cancel){
                        focusView.requestFocus();
                    }else{
                        SignupActivity.user = new User(firstName,lastName,username,emailDetail,passcode,0);

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new VerifyEmailFragment()).commit();

                    }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        password.setText("");

    }


    private boolean passwordValid(String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*+.=]).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }





    private boolean isEmailValid(String emailDetail){
        return(android.util.Patterns.EMAIL_ADDRESS.matcher(emailDetail).matches());
    }


    private boolean checkPassword(String password , String password2 ){
        {
            boolean cancel = false;
            // Check for a valid password, if the user entered one.

            if (TextUtils.isEmpty(password2)) {
                //CpasswordLayout.setError(getString(R.string.error_field_required));
                focusView = CpasswordLayout;
                cancel = true;
            }
            else if (!password2.equals(password)) {
                passwordLayout.setError("password doesn't match ");
                focusView = CpasswordLayout;
                cancel = true;
            }

            if (TextUtils.isEmpty(password)) {
                //passwordLayout.setError(getString(R.string.error_field_required));
                focusView = passwordLayout;
                cancel = true;
            }
            else if (password.length() < 8) {
                passwordLayout.setError("minimum password length is * chars");
                focusView = passwordLayout;
                cancel = true;
            }
            else if (!passwordValid(password))
            {
                passwordLayout.setError("Weak Password  : eg. @Joe123");
                focusView = passwordLayout;
                cancel = true;
            }

            return cancel ;
        }
    }

    class CustomTextWatcher implements TextWatcher{
        EditText editText;

        final int fid = fName.getId();
        final int lid = lName.getId();
        final int passid = password.getId();
        final int cpassid = Cpassword.getId();
        final int emailid = email.getId();

        public CustomTextWatcher(EditText view){
            editText =  view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            if(fName.getId() == editText.getId()){
                if(TextUtils.isEmpty(s)){
                    fNameLayout.setError("Field Required");
                }else{
                    fNameLayout.setError(null);
                }
            }
            else if(lName.getId() == editText.getId()){
                if(TextUtils.isEmpty(s)){
                    lNameLayout.setError("Field Required");
                }else{
                    lNameLayout.setError(null);
                }
            }
            else if(email.getId() == editText.getId()){
                if(TextUtils.isEmpty(s)){
                    emailLayout.setError("Field Required");
                }
                else if(!isEmailValid(s.toString())){
                    emailLayout.setError("Invalid Email Address");
                }
                else emailLayout.setError(null);
            }
            else if(password.getId() == editText.getId()){
                if(TextUtils.isEmpty(s)){
                    passwordLayout.setError("Field Required ");
                }
                else if(s.length()< 8){
                    passwordLayout.setError("Minimum password length is 8");
                }
                else if(!passwordValid(s.toString())){
                    passwordLayout.setError(" Weak Password ");
                }
                else passwordLayout.setError(null);
            }
            else if(Cpassword.getId() == editText.getId()){
                if(TextUtils.isEmpty(s)){
                    CpasswordLayout.setError("Field Required ");
                }else if(!s.toString().equals(password.getText().toString())){
                    CpasswordLayout.setError("Password doesn't match");
                }else CpasswordLayout.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}

