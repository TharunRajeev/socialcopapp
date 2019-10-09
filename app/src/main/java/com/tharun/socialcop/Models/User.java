package com.tharun.socialcop.Models;

public class User {
    String name;
    String username;
    String profile;
    boolean isAuthorized;
    boolean isVerififed;

    public User(String name, String username, String profile, boolean isAuthorized, boolean isVerififed) {
        this.name = name;
        this.username = username;
        this.profile = profile;
        this.isAuthorized = isAuthorized;
        this.isVerififed = isVerififed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
