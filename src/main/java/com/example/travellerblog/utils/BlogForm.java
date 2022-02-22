package com.example.travellerblog.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BlogForm {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private String userName;
    private String name;
    private String description;
    private LocalDate date;
    private String dateString;
    private MultipartFile coverImage;

    public BlogForm(String userName, String name, String description, LocalDate date, String dateString, MultipartFile coverImage) {
        this.userName = userName;
        this.name = name;
        this.description = description;
        this.date = date;
        this.dateString = dateString;
        this.coverImage = coverImage;
    }

    public BlogForm(String name, String description, String dateString, MultipartFile coverImage) {
        this.name = name;
        this.description = description;
        this.date = LocalDate.parse(dateString, formatter);
        this.dateString = dateString;
        this.coverImage = coverImage;
    }

    public BlogForm() {
        this.userName = "";
        this.name = "";
        this.description = "";
        this.dateString = "";
        this.date = null;
        this.coverImage = null;
    }

    public Pair<Integer, String> validateForm () {
        if (this.date == null) return Pair.of(1, "Date Field is empty");
        if (this.coverImage == null) return Pair.of(1, "Cover Image not supplied");
        if(this.name.length() > 100) return Pair.of(1, "Name Length exceeds 100 character limit");
        if (this.date.isAfter(LocalDate.now())) return Pair.of(1, "Date exceeds the Current Date");
        if (this.coverImage == null) return Pair.of(1, "Image File not uploaded");
        if (!FilenameUtils.isExtension(this.coverImage.getOriginalFilename(),
                "jpeg", "jpg", "png")) return Pair.of(1, "Cover Image doesn't match following types : JPEG/JPG/PNG");
        System.out.println("Size of the uploaded File: " + this.coverImage.getSize() * (9.5 * 1e-7));
        if (this.coverImage.getSize() * (9.5 * 1e-7) > 5.00) return Pair.of(1, "Cover Image size exceeds 5Mb limit");
        return Pair.of(2, "Your Blog has been submitted. Press on View Blogs to view");
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

    public String getDateString() {
        return dateString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        setDate(LocalDate.parse(dateString, formatter));
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCoverImage(MultipartFile coverImage) {
        this.coverImage = coverImage;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                "formatter=" + formatter +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", dateString='" + dateString + '\'' +
                ", coverImage=" + coverImage +
                '}';
    }
}
