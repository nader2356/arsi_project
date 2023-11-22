package com.example.util;

import com.example.dto.responseDto.UploadFileDetails;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Data
public class FileStorageService {



    @Value("${file.upload-dir}")
    private String fileStorageLocation;

    public UploadFileDetails storeFile(MultipartFile file, String relativePath) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("mahoch mawjoud ************"+fileName);
            }

            relativePath = relativePath + "/" + fileName;
            Path targetLocation = Paths.get(fileStorageLocation).resolve(relativePath).normalize();
            
            File uploadDir = new File(fileStorageLocation);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Assurez-vous que le répertoire cible existe, sinon, créez-le
            if (!Files.exists(targetLocation)) {
                Files.createDirectories(targetLocation);
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            String fileDisplayUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/display")
                    .queryParam("path", relativePath)
                    .toUriString();



            return new UploadFileDetails(fileName, fileDisplayUri, file.getContentType(), file.getSize());
        } catch (IOException ex) {

            throw new RuntimeException("mahoch mawjoud ************"+fileName);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
        	 Path imagePath = Paths.get(fileStorageLocation).resolve(fileName);
             System.out.println(imagePath);
             Resource resource = new ClassPathResource(fileStorageLocation+"\\"+fileName);

             if (resource.exists() && resource.isReadable()) {
                 HttpHeaders headers = new HttpHeaders();
                 headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // Modify the content type as needed
                return resource;
            } else {
            	 throw new RuntimeException("mahoch mawjoud ************");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mahoch mawjoud ************");
        }
    }



}
