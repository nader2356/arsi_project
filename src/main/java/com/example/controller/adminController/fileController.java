package com.example.controller.adminController;

import com.example.service.fileService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(Constants.APP_ROOT + "/file")
@RequiredArgsConstructor
@Api(tags = "files ")
@CrossOrigin("*")
public class fileController {

    private final fileService service;

    @PostMapping(value = "uploadImage")
    public ResponseEntity<String> uploadImage(@PathParam("file") MultipartFile file){
        return ResponseEntity.ok( service.uploadImage(file));
    }

    @GetMapping("img/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {


        Resource resource = service.serveFile(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Modify the content type as needed
                .body(resource);
    }
    @PostMapping(value = "uploadPDF")
    public ResponseEntity<String> uploadPDF(@PathParam("file") MultipartFile file){
        return ResponseEntity.ok(service.uploadPDF(file));
    }

    @GetMapping("PDF/{filename:.+}")
    public ResponseEntity<Resource> servePDF(@PathVariable String filename) {


        Resource resource = service.serveFile(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf") // Modify the content type as needed
                .body(resource);
    }
}
