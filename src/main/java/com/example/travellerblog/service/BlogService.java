/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-20
 */

package com.example.travellerblog.service;

import com.example.travellerblog.model.Blog;
import com.example.travellerblog.utils.BlogForm;

import java.io.IOException;
import java.util.List;


/**
 * BlogService provides Service layer abstraction to implement business logic related to management
 * of blog item or list of blog items.
 */
public interface BlogService {

    /**
     * Method to preform save Operation on form which it receives as parameter.
     * This involves converting the BlogForm to BlogModel and calling the BlogRepository to save the blog.
     *
     * @param form blog form to be saved
     * @throws IOException
     */
    void saveBlog(BlogForm form) throws IOException;


    /**
     * Polymorphic function that performs update on the existing blog of given {id} with the help of
     * new blog form which is receives as parameter
     *
     * @param form blog form representing updated data
     * @param id id of the old blog which needs to be updated
     * @throws IOException
     */
    void saveBlog(BlogForm form, String id) throws IOException;

    /**
     * Method to get the List of blogItems saved in database of the user of given {userName}.
     *
     * @param userName Name of the user from whose blog needs to be retrieved
     * @return List of Blog Model items.
     */
    List<Blog> getBlogList(String userName);


    /**
     * Method to load the image file from the path provided. Note that the path provided is relative with respect to web-app.
     * The method must return valid byte array representing the content of image.
     *
     * @param imgPath Path of the image file to be loaded
     * @return byte array representing image file
     * @throws IOException if loading of image fails
     */
    byte[] loadImageFile(String imgPath) throws IOException;


    /**
     * Method to delete Individual blogItem of given {id}
     *
     * @param Id id of the blogItem
     */
    void deleteBlogItem(String Id);

    /**
     * Method to retrieve individual BlogItem from the database
     *
     * @param Id id of the blogItem
     * @return Blog item
     * @throws RuntimeException if blog of specified id is not found
     */
    Blog getBlogItem(String Id) throws RuntimeException;
}
