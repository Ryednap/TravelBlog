package com.example.travellerblog.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageStorageService {
    public void init() throws IOException;
    public String saveImage(MultipartFile file) throws IOException;
    public MultipartFile loadImage(String imgPath, boolean fullPath) throws IOException;
    public void delete(String imgPath) throws IOException;
}
