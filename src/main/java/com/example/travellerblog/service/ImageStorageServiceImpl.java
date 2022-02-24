/**
 * @author Ujjwal Pandey
 * @version 1.1
 * @since 2022-02-19
 */

package com.example.travellerblog.service;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


/**
 * Implementation of {@link ImageStorageService} interface as service bean which is used by BlogService to deal with
 * CRUD operation relating to cover Image file
 *
 * @see com.example.travellerblog.service.ImageStorageService
 * @see BlogServiceImpl
 */

@Service
public class ImageStorageServiceImpl implements ImageStorageService{
    /**
     * Path variable referencing to image directory. This directory is with respect to root of our web-app
     */
    private final Path imageDir = Paths.get("images");

    /**
     * <div style="margin-bottom: 5px;">
     *     Creates Directory from {imageDir} path. This is mentioned as PostConstruct as the directory creation is done
     *     only once after the bean has been initialized.
     * </div>
     *
     * {@inheritDoc}
     * @throws IOException when unable to create directory
     */
    @Override
    @PostConstruct
    public void init() throws IOException {
        if (!Files.exists(imageDir)) Files.createDirectory(imageDir);
    }


    /**
     * <div style="margin-bottom: 10px">
     *     Method to save Image File if the File doesn't already exists in the directory.
     *     Whether the file already exits or not the relative path is returned.
     * </div>
     *
     * {@inheritDoc}
     * @param file File of type MultiPartFile to be saved
     * @return String: relative path where the image file is stored
     * @throws IOException
     */
    @Override
    public String saveImage(MultipartFile file) throws IOException {
        if (!Files.exists(this.imageDir.resolve(Objects.requireNonNull(file.getOriginalFilename()))))
            Files.copy(file.getInputStream(), this.imageDir.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        return this.imageDir.resolve(file.getOriginalFilename()).toString();
    }


    /**
     * Method to load the image File
     * {@inheritDoc}
     * @param imgPath String: Path of the image file where the file is located
     * @param fullPath Boolean: represents whether the provided {imgPath} is relative or full path
     * @return
     * @throws IOException
     */
    @Override
    public MultipartFile loadImage(String imgPath, boolean fullPath) throws IOException {
        // Load the imgFile from the path provided maybe relative or full path.
        File imgFile = fullPath ? Paths.get(imgPath).toFile() :this.imageDir.resolve(imgPath).toFile();

        /**
         * Create a File Item container which represents  a file or form item from "multipart/form-data" from POST request
         * The content from {imgFile} will be transferred to this item container the parameter passed to constructor of DiskFileItem are
         * The parameters passed to constructor are as follows:
         * {
         *          "file" :    fieldName in form
         *          "image/png" :  contentType
         *          false:  isFormField
         *          imgPath:     Name of the file
         *          (int)imgFile.length():  Size of the file
         * }
         * This file item will be converted to MultiPartFile to be sent on to server
          */
        FileItem imgFileItem = new DiskFileItem("file", "image/png", false,
                imgPath, (int)imgFile.length(), imgFile.getParentFile());

        // transfer the content of {imgFile} to {imgFileItem} through stream pipe without loading into memory
        new FileInputStream(imgFile).transferTo(imgFileItem.getOutputStream());
        return new CommonsMultipartFile(imgFileItem);
    }

    /**
     * {@inheritDoc}
     * @param imgName
     * @throws IOException
     */
    @Override
    public void delete(String imgName, boolean fullPath) throws IOException {
        Files.delete(fullPath ? Path.of(imgName) : this.imageDir.resolve(imgName));
    }
}
