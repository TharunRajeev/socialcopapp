package com.tharun.socialcop.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String profile;
    private String email;
    private String password;
    boolean isAuthorized;
    boolean isVerififed;

    int verificationCode;

    public User(String firstname, String lastname, String username, String email, String password,int verificationCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, String username, String profile, boolean isAuthorized, boolean isVerififed) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.profile = profile;
        this.isAuthorized = isAuthorized;
        this.isVerififed = isVerififed;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public boolean isVerififed() {
        return isVerififed;
    }

    public void setVerififed(boolean verififed) {
        isVerififed = verififed;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }
}
