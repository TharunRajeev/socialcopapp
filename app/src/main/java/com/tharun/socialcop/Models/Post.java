package com.tharun.socialcop.Models;

import java.util.List;

public class Post {

    private String parentid;
    private String pid;
    private User user;
    private String description;
    private String category;
    private String location;
    private String image;
    private String type;
    private boolean accepted;
    private int status;
    private boolean upvoted;
    private int upvotes;
    private boolean plusoned;
    private int plusones;
    private List<Post> comments;
    private long createdAt;


    public Post(String parentid, String description, String category, String location, String image, String type) {
        this.parentid = parentid;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.type = type;
    }

    public Post(String parentid,String pid, User user, String description, String category, String location, String image, String type, boolean accepted, int status, boolean upvoted, int upvotes, boolean plusoned, int plusones, List<Post> comments, long createdAt) {
        this.parentid = parentid;
        this.pid = pid;
        this.user = user;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.type = type;
        this.accepted = accepted;
        this.status = status;
        this.upvoted = upvoted;
        this.upvotes = upvotes;
        this.plusoned = plusoned;
        this.plusones = plusones;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isUpvoted() {
        return upvoted;
    }

    public void setUpvoted(boolean upvoted) {
        this.upvoted = upvoted;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public boolean isPlusoned() {
        return plusoned;
    }

    public void setPlusoned(boolean plusoned) {
        this.plusoned = plusoned;
    }

    public int getPlusones() {
        return plusones;
    }

    public void setPlusones(int plusones) {
        this.plusones = plusones;
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}

