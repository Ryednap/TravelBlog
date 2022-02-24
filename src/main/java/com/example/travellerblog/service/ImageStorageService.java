/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-19
 */

package com.example.travellerblog.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *  ImageStoreService provides service related to loading, saving and deleting image Files
 */
public interface ImageStorageService {


    /**
     * Init method used for Initializing bean which will be implementing this interface
     * This initialization can be anything from directory creation to variable initialization
     *
     * @throws IOException when Initialization related to files and directory is performed
     */
    void init() throws IOException;


    /**
     * Method that preforms saving operation on MultipartFile which is passed as parameter.
     * It must return relative location of the path where file is saved
     *
     * @param file File of type MultiPartFile to be saved
     * @return String : relative location where file is saved
     * @throws IOException when File is already present or unable to find the directory where we are required to save
     */
    String saveImage(MultipartFile file) throws IOException;


    /**
     * Method that loads the file from the path which is provided in args. The path maybe be relative with respect
     * to the web application, or we may notify the method about full path by passing a boolean parameter.
     * If file loading is successful then it returns that file or throws an exception.
     *
     * @param imgPath String: Path of the image file where the file is located
     * @param fullPath Boolean: represents whether the provided {imgPath} is relative or full path
     * @return MultipartFile: If file loading is successfully return that file as MultiPartFile.
     * @throws IOException If failure in finding or loading file.
     */
    MultipartFile loadImage(String imgPath, boolean fullPath) throws IOException;


    /**
     * Method that deletes the file from the path which is provided in args. the path is request to absolute the web-application
     *
     * @param imgPath String: Path of the image File where file is located
     * @param fullPath Boolean: represents whether the provided {imgPath} is relative or full path
     * @throws IOException if prescribed operation fails
     */
    void delete(String imgPath, boolean fullPath) throws IOException;
}
