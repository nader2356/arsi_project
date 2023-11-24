package com.example.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface fileService {

    String uploadImage(MultipartFile file);
    Resource serveFile(String fileName);

    String uploadPDF(MultipartFile file);


}
