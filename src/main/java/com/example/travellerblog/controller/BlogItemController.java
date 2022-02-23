package com.example.travellerblog.controller;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import com.example.travellerblog.utils.BlogItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/blogItem")
public class BlogItemController {
    private final BlogService blogService;

    @Autowired
    public BlogItemController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value="/view")
    public ModelAndView getBlogEditItemPage(@ModelAttribute("blogItemTemplate") BlogItemTemplate itemTemplate) {
        String id = itemTemplate.getId();
        Blog blogItem = blogService.getBlogItem(id);
        return new ModelAndView("blogItem").addObject("blogItem", blogItem);
    }


    @GetMapping(value = "/edit")
    public ModelAndView getBlogItemPage(@ModelAttribute("blotItemTemplate") BlogItemTemplate itemTemplate) {
        Blog blog = blogService.getBlogItem(itemTemplate.getId());
        BlogForm blogForm = new BlogForm(blog);
        System.out.println(blog.getId());
        return new ModelAndView("blogItemEdit").addObject("blogForm", blogForm)
                .addObject("id", blog.getId()).addObject("initImageLoc", blog.getCoverImageLocation());
    }

    @GetMapping(value = "/images/{imgPath}")
    public ModelAndView getImage(@PathVariable("imgPath") String imgPath) {
        return new ModelAndView("redirect:/blog/images/" + imgPath);
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteBlogItem(@ModelAttribute("blogItemTemplate") BlogItemTemplate blog, RedirectAttributes redirectAttributes) {
        String userName = blog.getUserName();
        String id = String.valueOf(blog.getId());
        blogService.deleteBlogItem(id);
        redirectAttributes.addAttribute("userName", userName);
        return new ModelAndView("redirect:/blog/blogList");
    }

    @PostMapping(value = "/update")
    public ModelAndView updateBlogItem(@RequestParam("id") String itemId, @ModelAttribute("blogForm") BlogForm form,
                                       RedirectAttributes redirectAttributes) throws IOException {

        blogService.saveBlog(form, itemId);
        redirectAttributes.addAttribute("userName", form.getUserName());
        return new ModelAndView("redirect:/blog/blogList");
    }
}
