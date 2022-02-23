/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-19
 */

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

/**
 * Controller that deals with request concerning individual blogItem. The request must follow /blogItem/* url specification.
 */
@RestController
@RequestMapping("/blogItem")
public class BlogItemController {

    /**
     * @see BlogService
     */
    private final BlogService blogService;

    @Autowired
    public BlogItemController(BlogService blogService) {
        this.blogService = blogService;
    }


    /**
     * Each {@link Blog} in the HTML template has view button whose request is targeted at this method's endpoint.
     * The request contains the {@link BlogItemTemplate} object with respective id of the {@link Blog} and username.
     * After retrieving the item from {@link #blogService} it returns the blogItem page with respective blog item
     *
     * @param itemTemplate {@link BlogItemTemplate} template with blog id and username
     * @return blogItem page with respective {@link Blog} embedded as object in view which will be rendered by thymeleaf
     */
    @GetMapping(value="/view")
    public ModelAndView getBlogEditItemPage(@ModelAttribute("blogItemTemplate") BlogItemTemplate itemTemplate) {
        String id = itemTemplate.getId();
        Blog blogItem = blogService.getBlogItem(id);
        return new ModelAndView("blogItem").addObject("blogItem", blogItem);
    }


    /**
     * Each {@link Blog} in the HTML template has update button whose request is targeted at this method's endpoint.
     * The request contains the {@link BlogItemTemplate} object with respective id of the {@link Blog} and username.
     * The edit HTML thymeleaf page requires the data from the old {@link Blog} and also requires empty {@link BlogForm}
     * in which the updated data will be recorded. The html page also requires the location of coverImage which will be rendered
     * as background in the page.
     *
     * @param itemTemplate {@link BlogItemTemplate} template with blog id and username
     * @return blogItemEdit HTML page with mentioned objects
     */
    @GetMapping(value = "/edit")
    public ModelAndView getBlogItemPage(@ModelAttribute("blotItemTemplate") BlogItemTemplate itemTemplate) {
        Blog blog = blogService.getBlogItem(itemTemplate.getId());
        BlogForm blogForm = new BlogForm(blog);

        return new ModelAndView("blogItemEdit").addObject("blogForm", blogForm)
                .addObject("id", blog.getId()).addObject("initImageLoc", blog.getCoverImageLocation());
    }


    /**
     * This method redirects the request of loading cover image to the {@link BlogController}
     * @see BlogController#getImage(String)
     * @param imgPath relative of the image
     * @return redirect view to the /blog/images endpoint
     */
    @GetMapping(value = "/images/{imgPath}")
    public ModelAndView getImage(@PathVariable("imgPath") String imgPath) {
        return new ModelAndView("redirect:/blog/images/" + imgPath);
    }


    /**
     * Each {@link Blog} in the HTML template has delete button whose request is targeted at this method's endpoint.
     * The request contains the {@link BlogItemTemplate} object with respective id of the {@link Blog} and username.
     * The method delegates the deletion work to {@link #blogService} and the redirect the request to {@link BlogController#viewBlogList(String username)}
     * by adding username as attributed which will later be appended as query parameter
     *
     * @param itemTemplate {@link BlogItemTemplate} template with blog id and username
     * @param redirectAttributes attributes related to redirection
     * @return redirected view to /blogList endpoint of {@link BlogController}
     * @see BlogController#viewBlogList(String)
     */
    @PostMapping(value = "/delete")
    public ModelAndView deleteBlogItem(@ModelAttribute("blogItemTemplate") BlogItemTemplate itemTemplate, RedirectAttributes redirectAttributes) {
        String userName = itemTemplate.getUserName();
        String id = String.valueOf(itemTemplate.getId());
        blogService.deleteBlogItem(id);
        redirectAttributes.addAttribute("userName", userName);
        return new ModelAndView("redirect:/blog/blogList");
    }


    /**
     * This method updates the old {@link Blog} of given {id} with the data from {@link BlogForm} form.
     * The update process is delegated to service layer, and if successful the request is redirected to
     * /blogList endpoint of {@link BlogController} otherwise exception is raised to be handled separately
     *
     * @param itemId id referencing old blog which needs to be updated
     * @param form new updated form containing updated record
     * @param redirectAttributes attributes related to redirection
     * @return redirected view to /blogList endpoint of {@link BlogController}
     * @throws IOException when {@link #blogService} fails to save the blog
     * @see BlogController#viewBlogList(String)
     */
    @PostMapping(value = "/update")
    public ModelAndView updateBlogItem(@RequestParam("id") String itemId, @ModelAttribute("blogForm") BlogForm form,
                                       RedirectAttributes redirectAttributes) throws IOException {

        blogService.saveBlog(form, itemId);
        redirectAttributes.addAttribute("userName", form.getUserName());
        return new ModelAndView("redirect:/blog/blogList");
    }
}
