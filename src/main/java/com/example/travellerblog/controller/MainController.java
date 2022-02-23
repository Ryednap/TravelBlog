/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-15
 */

package com.example.travellerblog.controller;

import com.example.travellerblog.utils.BlogForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main Controller that serves the home endPoint of the web application
 */
@RestController
public class MainController {

    /**
     * Serves the index page to view level. The indexPage contains 3 attributes
     * <ul>
     *     <li>blog: Empty {@link BlogForm} to be utilized in thymeleaf html template</li>
     *     <li>status: Current Status of {@link BlogForm} </li>
     *     <li>message: If the form has been validated then {model} param contains message attributed</li>
     * </ul>
     *
     * @param model model that carries data attributes from another endpoint if that endpoint has redirected to here.
     * @return indexPage view
     */
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
