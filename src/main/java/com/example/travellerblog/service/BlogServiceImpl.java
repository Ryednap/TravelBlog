/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-20
 */

package com.example.travellerblog.service;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.repository.BlogRepository;
import com.example.travellerblog.utils.BlogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service Layer implementation of the {@link BlogService} that handles operation related to managing
 * blog items and blog forms.
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public BlogServiceImpl(BlogRepository repository, ImageStorageService imageStorageService) {
        this.blogRepository = repository;
        this.imageStorageService = imageStorageService;
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * The method saves the blogForm by converting a new Blog Model from the fields of the form and saving image
     * file using {@link ImageStorageService#saveImage(MultipartFile)}  whose return value of relative path is used as
     * coverImageLocation for {@link Blog}.
     *
     * @param form blog form to be saved
     * @throws IOException which it receives from imageStorageService
     */
    @Override
    public void saveBlog(BlogForm form) throws IOException {
        blogRepository.saveAndFlush(new Blog(
                form.getUserName(),
                form.getName(),
                form.getDate(),
                form.getDescription(),
                imageStorageService.saveImage(form.getCoverImage())
        ));
    }

    /**
     * {@inheritDoc}
     * <br><br>
     * This method updates the already existing blog with the given new form data
     * If the new form fields are not null or empty we updated that field otherwise keep old field
     *
     * @param form blog form representing updated data
     * @param id id of the old blog which needs to be updated
     * @throws IOException
     */
    @Override
    public void saveBlog(BlogForm form, String id) throws IOException {

        // old blog item which needs to be updated
        Blog oldBlog = blogRepository.getById(Long.parseLong(id));

        if (form.getName() != null) oldBlog.setName(form.getName());
        if (form.getDate() != null) oldBlog.setDate(form.getDate());
        if (form.getDescription() != null) oldBlog.setDescription(form.getDescription());
        if (form.getCoverImage() != null && form.getCoverImage().getSize() > 0) {

            // Delete the old_image
            imageStorageService.delete(oldBlog.getCoverImageLocation());
            // update to the new_image
            oldBlog.setCoverImageLocation(imageStorageService.saveImage(form.getCoverImage()));
        }

        blogRepository.saveAndFlush(oldBlog);
    }


    /**
     * {@inheritDoc}
     * <p>
     *     Returns the sorted List of {@link Blog} items by date in ascending order
     * </p>
     * @param userName Name of the user from whose blog needs to be retrieved
     * @return List of blog Items
     */
    @Override
    public List<Blog> getBlogList(String userName) {
        List<Blog> blogByUserName = blogRepository.findBlogByUserName(userName);
        blogByUserName.sort(Comparator.comparing(Blog::getDate));
        return blogByUserName;
    }


    /**
     * {@inheritDoc}
     * @param imgPath Path of the image file to be loaded
     * @return byte array representation of image file
     * @throws IOException
     */
    @Override
    public byte[] loadImageFile(String imgPath) throws IOException {
        MultipartFile imgFile = imageStorageService.loadImage(imgPath, false);
        return imgFile.getBytes();
    }

    /**
     * {@inheritDoc}
     * @param Id id of the blogItem
     */
    @Override
    public void deleteBlogItem(String Id) {
        blogRepository.deleteById(Long.parseLong(Id));
    }


    /**
     * {@inheritDoc}
     * @param Id id of the blogItem
     * @return blog item
     * @throws RuntimeException
     */
    @Override
    public Blog getBlogItem(String Id) throws RuntimeException{
        Optional<Blog> optionalBlog =  blogRepository.findById(Long.parseLong(Id));
        if (optionalBlog.isEmpty()) throw new RuntimeException("Blog Not Found");
        return optionalBlog.get();
    }

}
