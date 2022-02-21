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

@Service
public class ImageStorageServiceImpl implements ImageStorageService{
    private final Path imageDir = Paths.get("images");

    @Override
    @PostConstruct
    public void init() throws IOException {
        if (!Files.exists(imageDir)) Files.createDirectory(imageDir);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        if (!Files.exists(this.imageDir.resolve(Objects.requireNonNull(file.getOriginalFilename()))))
            Files.copy(file.getInputStream(), this.imageDir.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        return this.imageDir.resolve(file.getOriginalFilename()).toString();
    }

    @Override
    public MultipartFile loadImage(String imgName, boolean fullPath) throws IOException {
        File imgFile = fullPath ? Paths.get(imgName).toFile() :this.imageDir.resolve(imgName).toFile();
        FileItem imgFileItem = new DiskFileItem("file", "image/png", false,
                imgName, (int)imgFile.length(), imgFile.getParentFile());

        new FileInputStream(imgFile).transferTo(imgFileItem.getOutputStream());
        return new CommonsMultipartFile(imgFileItem);
    }

    @Override
    public void delete(String imgName) throws IOException {
        Files.delete(this.imageDir.resolve(imgName));
    }
}
