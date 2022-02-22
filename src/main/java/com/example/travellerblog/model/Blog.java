package com.example.travellerblog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "userName")
    private final String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description", columnDefinition = "TEXT")
    private  String description;

    @Column(name = "cover_image_loc")
    private String coverImageLocation;

    public Blog(String userName, String name, LocalDate date, String description, String coverImageLocation) {
        this.userName = userName;
        this.name = name;
        this.date = date;
        this.description = description;
        this.coverImageLocation = coverImageLocation;
    }
    public Blog() {
        this.Id = null;
        this.userName = null;
        this.date = null;
        this.name = "";
        this.description = "";
        this.coverImageLocation = "";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return Objects.equals(getId(), blog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Blog{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", coverImageLocation='" + coverImageLocation + '\'' +
                '}';
    }
}
