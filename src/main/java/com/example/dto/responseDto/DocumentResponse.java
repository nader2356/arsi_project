package com.example.dto.responseDto;

import com.example.entity.Document;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DocumentResponse {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String URLDocument;
    private String typedoc;
    private Instant createdAt;
    private Instant updatedAt;

    public static DocumentResponse makeDocument(Document document) {
        return DocumentResponse.builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .image(document.getImage())
                .URLDocument(document.getURLDocument())
                .typedoc(document.getTypedoc())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }}