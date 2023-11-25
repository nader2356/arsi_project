package com.example.service;


import com.example.dto.requestDto.MediaRequest;

import com.example.dto.responseDto.MediaResponse;

import java.util.List;

public interface MediaService {

    void addMedia(MediaRequest request);

    List<MediaResponse> getAllMedia();

    MediaResponse getMediaById(Long id);

    void deleteMedia(Long id);

    void updateMedia(Long id, MediaRequest request);

}