package com.example.travellerblog.controller;

import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(value = "/form")
    public ModelAndView validateForm(@ModelAttribute("blog") BlogForm form, RedirectAttributes redirectAttributes) throws IOException {
        Pair<Integer, String> validationMessage = form.validateForm();
        redirectAttributes.addFlashAttribute("status", validationMessage.getFirst());
        redirectAttributes.addFlashAttribute("message", validationMessage.getSecond());
        if (validationMessage.getFirst() == 2) {
            blogService.saveBlog(form);
        }
        return new ModelAndView("redirect:/index");
    }

    @PostMapping(value = "/credentials")
    public ModelAndView validateCredentials(@RequestParam("user_name") String userName) {
        return new ModelAndView("redirect:/blogList");
    }

    @GetMapping(value="/blogList")
    public ModelAndView getBlogList() {
        return new ModelAndView("blogList");
    }

    @DeleteMapping(value = "/blogItem")
    public ModelAndView deleteBlogItem() {
        return new ModelAndView("redirect:/blogList");
    }
}
