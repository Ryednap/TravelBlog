package com.example.travellerblog.controller;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public ModelAndView validateCredentials(@RequestParam("user_name") String userName, RedirectAttributes redirectAttributes) {
        List<Blog> blogItemList = blogService.getBlogList(userName);
        redirectAttributes.addFlashAttribute("blogItemList", blogItemList);
        return new ModelAndView("redirect:/blog/blogList");
    }

    @GetMapping(value = "/blogList")
    public ModelAndView viewBlogList(Model model) {
        return new ModelAndView("blogList").addObject("blogItemList", model.getAttribute("blogItemList"));
    }

    @GetMapping(value = "/images/{imgPath}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imgPath") String imgPath) throws IOException {
        byte[] imageArrayBytes = blogService.loadImageFile(imgPath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageArrayBytes);
    }

    @GetMapping(value = "/blogItem")
    public ModelAndView getBlogItem() {
        return new ModelAndView("blogItem");
    }

    @GetMapping(value="/blog/blogEdit")
    public ModelAndView getBlogEditPage () {
        return new ModelAndView("blogItemEdit");
    }

    @DeleteMapping(value = "/blogItem")
    public ModelAndView deleteBlogItem() {
        return new ModelAndView("redirect:/blogList");
    }
}
