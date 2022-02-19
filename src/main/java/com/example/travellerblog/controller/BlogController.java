package com.example.travellerblog.controller;

import com.example.travellerblog.utils.BlogForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/blog")
public class BlogController {
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView showIndexPage() {
        ModelAndView indexPage = new ModelAndView("index");
        indexPage.addObject("blog", new BlogForm());
        return indexPage;
    }

}
