package com.example.travellerblog.utils;

public class BlogItemClassUtil {
    private String id;
    private String userName;

    public BlogItemClassUtil(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public BlogItemClassUtil() {
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
