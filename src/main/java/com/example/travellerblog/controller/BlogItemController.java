package com.example.travellerblog.controller;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogItemClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/blogItem")
public class BlogItemController {
    private final BlogService blogService;

    @Autowired
    public BlogItemController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = "/")
    public ModelAndView getBlogItem() {
        return new ModelAndView("blogItem");
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteBlogItem(@ModelAttribute("blogItem_delete")BlogItemClassUtil blog, RedirectAttributes redirectAttributes) {
        String userName = blog.getUserName();
        String id = String.valueOf(blog.getId());
        blogService.deleteBlogItem(id);
        redirectAttributes.addAttribute("userName", userName);
        return new ModelAndView("redirect:/blog/blogList");
    }
}
