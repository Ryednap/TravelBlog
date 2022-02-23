package com.example.travellerblog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Description: Model of MVC design pattern.
 *              Defines the Data Structure representation of Individual Blog Item stored in MYSQL database.
 *              This Data Structure is stored in DB with table name "blog" and Primary Key {Id}.
 *
 *
 * Definition: Each Blog Item has unique {Id} which is the primary key. Each Blog belongs to certain user {userName}
 *             Now same user can have multiple blogs each identified by {name}, {date}, {description}, {coverImage}
 *
 *
 * Note: We can further normalize the database by creating a new table for userName, but to keep things simple we only
 *       maintain single table.
 */

@Entity
@Table(schema = "blog")
public class Blog {

    /**
     * Unique Id of each Blog
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    /**
     * Name of the user to whom the blog belongs to
     */

    @Column(name = "userName")
    private final String userName;

    /**
     * Travel Blog Name (maybe destination name, topic name etc.)
     */

    @Column(name = "name")
    private String name;

    /**
     * Travel Date
     */
    @Column(name = "date")
    private LocalDate date;

    /**
     * Description of the Travel Blog
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private  String description;

    /**
     * Relative Path of Cover Image which is stored on disk in local computer or server.
     */
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

    public String getFullDateString() {
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return this.date.format(fullFormatter);
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
