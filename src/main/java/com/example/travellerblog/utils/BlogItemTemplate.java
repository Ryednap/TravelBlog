package com.example.travellerblog.utils;

/**
 * Mini Template Class which is basically a subset of {@link BlogForm}, but only with {id} and {userName} as Field.
 * This template is used To identify individual blog of respective {id} belonging to respective person {userName}.
 * Mostly used as Object in implementing Edit, View and Delete Blog feature for thymeleaf HTML template
 */

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
