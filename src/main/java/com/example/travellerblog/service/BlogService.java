package com.example.travellerblog.service;

import com.example.travellerblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository repository) {
        this.blogRepository = repository;
    }

}
