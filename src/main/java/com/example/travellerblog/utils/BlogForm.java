package com.example.travellerblog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Objects;

public class BlogForm {
    private final String name;
    private final String description;
    private final LocalDate date;
    private final MultipartFile coverImage;

    public BlogForm(String name, String description, LocalDate date, MultipartFile coverImage) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.coverImage = coverImage;
    }
    public BlogForm() {
        this.name = "";
        this.description = "";
        this.date = null;
        this.coverImage = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public MultipartFile getCoverImage() {
        return coverImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogForm blogForm = (BlogForm) o;
        return Objects.equals(getName(), blogForm.getName()) && Objects.equals(getDescription(), blogForm.getDescription()) && Objects.equals(getDate(), blogForm.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getDate());
    }

    @Override
    public String toString() {
        return "BlogForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
