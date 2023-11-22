package com.example.controller;

import com.example.dto.responseDto.UploadFileDetails;
import com.example.util.FileStorageService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@RestController
@RequestMapping(Constants.APP_ROOT + "/file")
@RequiredArgsConstructor
@Api(tags = "image Management")
@CrossOrigin("*")
public class FileController {

    @Autowired
    private final FileStorageService fileStorageService;

    @PostMapping()
    public ResponseEntity<UploadFileDetails> storeFile(@PathParam("file") MultipartFile file){

        UploadFileDetails uploadFileDetails = fileStorageService.storeFile(file, "salem");

        return ResponseEntity.ok(uploadFileDetails);

    }


    @GetMapping("/display")
    public ResponseEntity<String> displayPDF(@RequestParam("path") String path) {
        String fullPath = fileStorageService.getFileStorageLocation() + "\\" + path;
        Resource resource = fileStorageService.loadFileAsResource(fullPath);

        if (resource.exists() && resource.isReadable()) {
            try {
                byte[] pdfBytes = Files.readAllBytes(resource.getFile().toPath());
                String base64PDF = Base64.getEncoder().encodeToString(pdfBytes);
                String embeddedPDF = "<embed width='100%' height='600' src='data:application/pdf;base64," + base64PDF + "' type='application/pdf'>";

                return ResponseEntity.ok(embeddedPDF);
            } catch (IOException e) {
                return ResponseEntity.ok("Error reading the PDF file.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
