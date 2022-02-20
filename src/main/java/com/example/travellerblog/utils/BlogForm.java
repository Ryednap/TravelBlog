package com.example.travellerblog.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public Pair<Boolean, String> validateForm () {
        if (this.date == null) return Pair.of(false, "Date Field is empty");
        if (this.coverImage == null) return Pair.of(false, "Cover Image not supplied");
        if(this.name.length() > 100) return Pair.of(false, "Name Length exceeds 100 character limit");
        if (this.date.isAfter(LocalDate.now())) return Pair.of(false, "Date exceeds the Current Date");
        if (FilenameUtils.isExtension(this.coverImage.getOriginalFilename(),
                "jpeg", "jpg", "png")) return Pair.of(false, "Cover Image doesn't match following types : JPEG/JPG/PNG");
        System.out.println("Size of the uploaded File: " + this.coverImage.getSize() * (9.5 * 1e-7));
        if (this.coverImage.getSize() * (9.5 * 1e-7) > 5.00) return Pair.of(false, "Cover Image size exceeds 5Mb limit");
        return Pair.of(true, "Registration Successful");
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
