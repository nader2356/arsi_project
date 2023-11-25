package com.example.dto.responseDto;

import com.example.entity.Media;
import com.example.util.enumData.MediaType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class MediaResponse {

    private Long id;
    private String title;
    private String description;
    private MediaType type;
    private String image;
    private String urlPost;
    private Instant createdAt;
    private Instant updatedAt;

    public static MediaResponse makeMedia(Media media) {


        return MediaResponse.builder()
                .id(media.getId())
                .title(media.getTitle())
                .description(media.getDescription())
                .type(media.getType())
                .image(media.getImage())
                .urlPost(media.getUrlPost())
                .createdAt(media.getCreatedAt())
                .updatedAt(media.getUpdatedAt()).build();
    }

}