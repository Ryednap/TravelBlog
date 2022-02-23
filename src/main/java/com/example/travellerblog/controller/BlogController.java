/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2020-02-16
 */

package com.example.travellerblog.controller;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.service.BlogService;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Controller that serves the /blog endpoint. Methods in this controller primarily deals with operations and
 * functions involving all blog as general
 */

@RestController
@RequestMapping("/blog")
public class BlogController {
    /**
     * @see BlogService
     */
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * This method is endpoint for validating form. The validation is delegated to
     * service layer and the result of the validation (status, message) is passed as flash attributes
     * to the redirected url.
     * @see BlogForm#validateForm() for info about (status, message) pair
     *
     * @param form Blog form attribute
     * @param redirectAttributes attributes to be redirected
     * @return redirect link or view to /index endpoint of {@link MainController#showIndexPage(Model)}
     * @throws IOException of service layers IoException
     */
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


    /**
     * This method accepts the post request at /credentials endpoint. The request at this endpoint is generally
     * targeted when the user wants to get the list of {@link Blog} associated with respective user. Hence, this
     * method redirects the request to {@link #viewBlogList(String)} after the adding the userName request parameter
     * to redirected attributes. The redirected attribute is appended as query parameter at the end of redirected URL.
     * <br><br>
     * For example:
     * <pre style="text-align: center;">
     *     If username is "Ujjwal" then the redirected url will become : localhost:8080/blog/blogList?userName=Ujjwal
     * </pre>
     *
     * @param userName request parameter representing name of user
     * @param redirectAttributes attributes to be redirected
     * @return redirected link or view to {@link #viewBlogList(String)} endpoint handler
     */
    @PostMapping(value = "/credentials")
    public ModelAndView validateCredentials(@RequestParam("user_name") String userName, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("userName", userName);
        return new ModelAndView("redirect:/blog/blogList");
    }


    /**
     * This method accepts the request parameter of userName and then delegates the work of retrieving {@link Blog} list
     * to the service layer and then adds the list as object to View layer to be rendered by thymeleaf in html template.
     *
     * @param userName name of the user whose {@link Blog} items needs to be retrieved.
     * @return blogList html page as view.
     */
    @GetMapping(value = "/blogList")
    public ModelAndView viewBlogList(@RequestParam("userName") String userName) {
        List<Blog> blogItemList = blogService.getBlogList(userName);
        return new ModelAndView("blogList").addObject("blogItemList", blogItemList);
    }

    /**
     * Endpoint that serves the request of returning image file to the client. The client must provide
     * the relative image location which is present in the {@link Blog#getCoverImageLocation()}. The work of loading the
     * image file is delegated to the service layer and if found the image file is a response with embedded image file is sent
     * to client, otherwise exception is thrown.
     * @param imgPath
     * @return response entity with image file as byte array
     * @throws IOException when {@link #blogService} fails to load the image
     */
    @GetMapping(value = "/images/{imgPath}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imgPath") String imgPath) throws IOException {
        byte[] imageArrayBytes = blogService.loadImageFile(imgPath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageArrayBytes);
    }
}
