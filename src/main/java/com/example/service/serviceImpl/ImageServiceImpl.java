package com.example.service.serviceImpl;

import com.example.entity.Image;
import com.example.repository.ImageRepository;
import com.example.service.ImageService;
import com.example.util.ImageUtility;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    @Override
    public void uploadImage(MultipartFile file) throws IOException {

        imageRepository.save(Image.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .image(ImageUtility.compressImage(file.getBytes())).build());

    }

    @Override
    public Image getImageDetails(String name) {
        Image dbImage = imageRepository.findByName(name).orElseThrow();
        return Image.builder()
                .name(dbImage.getName())
                .type(dbImage.getType())
                .image(ImageUtility.decompressImage(dbImage.getImage()))
                .build();
    }


}