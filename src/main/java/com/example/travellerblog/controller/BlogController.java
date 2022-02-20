package com.example.travellerblog.controller;

import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value="/blog")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView showIndexPage() throws IOException {
        ModelAndView indexPage = new ModelAndView("index");
        indexPage.addObject("blog", new BlogForm());
        return indexPage;
    }

    // Todo Implement Error handling
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView validateForm(@ModelAttribute("blog") BlogForm form) throws IOException {
        Pair<Boolean, String> validationMessage = form.validateForm();
        if (validationMessage.getFirst()) {
            blogService.saveBlog(form);
        }
        return new ModelAndView("Hello");
    }
}
