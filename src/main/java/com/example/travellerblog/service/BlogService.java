package com.example.travellerblog.service;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.repository.BlogRepository;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public BlogService(BlogRepository repository, ImageStorageService imageStorageService) {
        this.blogRepository = repository;
        this.imageStorageService = imageStorageService;
    }

    public void saveBlog(BlogForm form) throws IOException {
        blogRepository.saveAndFlush(new Blog(
                form.getName(),
                form.getDate(),
                form.getDescription(),
                imageStorageService.saveImage(form.getCoverImage())
        ));
    }
}
