package com.example.travellerblog.repository;

import com.example.travellerblog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, String> {
    public List<Blog> findBlogByUserName(String userName);
}
