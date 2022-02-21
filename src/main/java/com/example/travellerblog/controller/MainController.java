package com.example.travellerblog.controller;

import com.example.travellerblog.utils.BlogForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MainController {

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public ModelAndView showIndexPage(Model model) {
        Integer status = (Integer) model.getAttribute("status");
        status = status == null ? 0 : status;
        ModelAndView indexPage = new ModelAndView("index");
        indexPage.addObject("blog", new BlogForm());
        indexPage.addObject("status", status);
        indexPage.addObject("message", model.getAttribute("message"));
        return indexPage;
    }
}
