package com.example.travellerblog.controller;

import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

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

    // Todo Implement Error handling
    @RequestMapping(value = "/blog/form", method = RequestMethod.POST)

    public ModelAndView validateForm(@ModelAttribute("blog") BlogForm form, RedirectAttributes redirectAttributes) throws IOException {
        Pair<Integer, String> validationMessage = form.validateForm();
        redirectAttributes.addFlashAttribute("status", validationMessage.getFirst());
        redirectAttributes.addFlashAttribute("message", validationMessage.getSecond());
        if (validationMessage.getFirst() == 2) {
            blogService.saveBlog(form);
        }
        return new ModelAndView("redirect:/index");
    }
}
