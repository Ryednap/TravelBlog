package com.example.travellerblog.service;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.repository.BlogRepository;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
                form.getUserName(),
                form.getName(),
                form.getDate(),
                form.getDescription(),
                imageStorageService.saveImage(form.getCoverImage())
        ));
    }
    public void saveBlog(BlogForm form, String id) throws IOException {
        System.out.println("Got Id : " + id);
        Blog oldBlog = blogRepository.getById(Long.parseLong(id));
        if (form.getName() != null) oldBlog.setName(form.getName());
        if (form.getDate() != null) oldBlog.setDate(form.getDate());
        if (form.getDescription() != null) oldBlog.setDescription(form.getDescription());
        if (form.getCoverImage() != null && form.getCoverImage().getSize() > 0) {
            oldBlog.setCoverImageLocation(imageStorageService.saveImage(form.getCoverImage()));
        }

        blogRepository.saveAndFlush(oldBlog);
    }

    public List<Blog> getBlogList(String userName) {
        return blogRepository.findBlogByUserName(userName);
    }

    public byte[] loadImageFile(String imgPath) throws IOException {
        MultipartFile imgFile = imageStorageService.loadImage(imgPath, false);
        return imgFile.getBytes();
    }

    public void deleteBlogItem(String Id) {
        blogRepository.deleteById(Long.parseLong(Id));
    }
    public Blog getBlogItem(String Id) throws RuntimeException{
        Optional<Blog> optionalBlog =  blogRepository.findById(Long.parseLong(Id));
        if (optionalBlog.isEmpty()) throw new RuntimeException("Blog Not Found");
        return optionalBlog.get();
    }

}
