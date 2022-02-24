package com.example.travellerblog;


import com.example.travellerblog.service.ImageStorageService;
import com.example.travellerblog.service.ImageStorageServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageStorageServiceTests {
    private ImageStorageService imageStorageService;

    @Before
    public void init() {
        this.imageStorageService = new ImageStorageServiceImpl();
    }

    @Test
    public void testALoadImage() throws IOException {
        MultipartFile file = imageStorageService.loadImage("testImages/testImage.png", true);
        assert (file != null);
        assert(file.getSize() != 0);
        assert(Objects.equals(file.getOriginalFilename(), "testImage.png"));
    }

    @Test
    public void testBSaveImage() throws IOException {
        if (!Files.exists(Paths.get("Images"))) Files.createDirectory(Paths.get("images"));

        File sampleImageFile = new File("testImages/testImage.png");
        assert (sampleImageFile.exists());
        FileItem imgFileItem = new DiskFileItem("file", "image/png", false,
                "testImage.png", (int)sampleImageFile.length(), sampleImageFile.getParentFile());
        new FileInputStream(sampleImageFile).transferTo(imgFileItem.getOutputStream());

        String savedFilename = imageStorageService.saveImage(new CommonsMultipartFile(imgFileItem));
        assert (new File(savedFilename).exists());
    }

    @Test
    public void testCDeleteImage() throws IOException {
        imageStorageService.delete("testImage.png", false);
        assert (!new File("images/testImage.png").exists());
    }
}
