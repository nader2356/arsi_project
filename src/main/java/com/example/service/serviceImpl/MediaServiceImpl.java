package com.example.service.serviceImpl;

import com.example.dto.requestDto.MediaRequest;
import com.example.dto.responseDto.CategoryResponse;
import com.example.dto.responseDto.MediaResponse;
import com.example.entity.Category;
import com.example.entity.Media;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.repository.MediaRepository;
import com.example.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    @Override
    public void addMedia(MediaRequest request) {

        Media media = Media.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .type(request.getType())
                .image(request.getImage())
                .urlPost(request.getUrlPost())
                .build();

        mediaRepository.save(media);

    }

    @Override
    public List<MediaResponse> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        List<MediaResponse> mediaResponses = new ArrayList<>();

        for (Media media : mediaList) {

            MediaResponse mediaResponse = MediaResponse.makeMedia(media);
            mediaResponses.add(mediaResponse);
        }
        return mediaResponses;
    }

    @Override
    public MediaResponse getMediaById(Long id) {
        Media media = mediaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this id[%s] is not exist", id)));

        return MediaResponse.makeMedia(media);
    }

    @Override
    public void deleteMedia(Long id) {

        if (!mediaRepository.existsById(id)) {
            throw new NotFoundException(String.format("this id[%s] is not exist", id));
        }
        mediaRepository.deleteById(id);

    }

    @Override
    public void updateMedia(Long id, MediaRequest request) {

        Media media = mediaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this id[%s] is not exist", id)));

        if (request.getTitle() != null) {
            media.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            media.setDescription(request.getDescription());
        }
        if (request.getType() != null) {
            media.setType(request.getType());
        }
        if (request.getImage() != null) {
            media.setImage(request.getImage());
        }
        if (request.getUrlPost() != null) {
            media.setUrlPost(request.getUrlPost());
        }


        mediaRepository.save(media);

    }
}
