package com.example.travellerblog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "blog")
public class Blog {
    @Id
    @Column(name = "name")
    private final String name;

    @Column(name = "date")
    private final LocalDate date;

    @Column(name = "description")
    private final String description;

    @Column(name = "cover_image_loc")
    private String coverImageLocation;

    public Blog(String name, LocalDate date, String description, String coverImageLocation) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.coverImageLocation = coverImageLocation;
    }
    public Blog() {
        this.name = "";
        this.date = null;
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverImageLocation() {
        return coverImageLocation;
    }

    public void setCoverImageLocation(String coverImageLocation) {
        this.coverImageLocation = coverImageLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(getName(), blog.getName()) && Objects.equals(getDate(), blog.getDate()) && Objects.equals(getDescription(), blog.getDescription()) && Objects.equals(getCoverImageLocation(), blog.getCoverImageLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate(), getDescription(), getCoverImageLocation());
    }

    @Override
    public String toString() {
        return "Blog{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", coverImageLocation='" + coverImageLocation + '\'' +
                '}';
    }
}
