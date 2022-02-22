package com.example.travellerblog.utils;

public class BlogItemTemplate {
    private String id;
    private String userName;

    public BlogItemTemplate(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public BlogItemTemplate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
