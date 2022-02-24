/**
 * @author Ujjwal
 * @version 1.1
 * @since 2022-02-19
 */

package com.example.travellerblog.utils;


import com.example.travellerblog.model.Blog;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents Blog Form that is passed as object to thymeleaf for capturing the input fields in html form.
 * Provides a wrapper around {@link Blog} Model at View level
 */
public class BlogForm {
    /**
     * Date String Formatter
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    /**
     * Name of the user who is posting the blog
     */
    private String userName;

    /**
     * Travel Blog Name
     */
    private String name;

    /**
     * Travel Blog description
      */
    private String description;

    /**
     * Date of Travel in Java LocalDate API
     */
    private LocalDate date;

    /**
     * Date of Travel in String Format
     */
    private String dateString;

    /**
     * Cover Image File from the form
     */
    private MultipartFile coverImage;


    /**
     * Copy Constructor to transfer the fields of @{link {@link Blog}} model to Blog Form
     *
     * @param blog blog model
     */
    public BlogForm(Blog blog) {
        this.userName = blog.getUserName();
        this.name = blog.getName();
        this.description = blog.getDescription();
        this.date = blog.getDate();
        this.dateString = blog.getDate().format(formatter);
        this.coverImage = null;
    }


    /**
     * Default No-Args Constructor for initializing empty object to passed in thymeleaf template
     */
    public BlogForm() {
        this.userName = "";
        this.name = "";
        this.description = "";
        this.dateString = "";
        this.date = null;
        this.coverImage = null;
    }


    /**
     * Method to self validate the form and return respective status and message after validation
     * {
     * status 0 : Although not present here, but we pass it to thymeleaf template indicating that form is empty
     * status 1 : Error in validation
     * status 2 : Ok
     * }
     *
     * @return Pair defining the (status, message) after form validation
     */
    public Pair<Integer, String> validateForm () {

        // date is empty
        if (this.date == null) return Pair.of(1, "Date Field is empty");
        // cover Image not provided
        if (this.coverImage == null) return Pair.of(1, "Cover Image not supplied");
        // Character limit for name field exceeds
        if (this.name.length() > 100) return Pair.of(1, "Name Length exceeds 100 character limit");
        // Date validation
        if (this.date.isAfter(LocalDate.now())) return Pair.of(1, "Date exceeds the Current Date");
        // Cover Image File extension check
        if (!FilenameUtils.isExtension(this.coverImage.getOriginalFilename(),
                "jpeg", "jpg", "png")) return Pair.of(1, "Cover Image doesn't match following types : JPEG/JPG/PNG");

        // Checking Size of coverImage (Note: by default getSize() returns in bytes, so we convert it to Mega bytes)
        if (this.coverImage.getSize() * (9.5 * 1e-7) > 5.00) return Pair.of(1, "Cover Image size exceeds 5Mb limit");

        // Default OK validation
        return Pair.of(2, "Your Blog has been submitted. Press on View Blogs to view");
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets cover image.
     *
     * @return the cover image
     */
    public MultipartFile getCoverImage() {
        return coverImage;
    }

    /**
     * Gets date string.
     *
     * @return the date string
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * Gets full date string.
     *
     * @return the full date string
     */
    public String getFullDateString() {
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return this.date.format(fullFormatter);
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets date string.
     *
     * @param dateString the date string
     */
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
