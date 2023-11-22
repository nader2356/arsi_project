package com.example.dto.responseDto;

import com.example.entity.Formation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormationResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String image;
    private Long maxOfParticipants;
    private Long numberOfParticipants;
    private String location;
    private String formateur;
    private Long price;
    private boolean status;
    private Instant createdAt;
    private Instant updatedAt;


    public static FormationResponse makeFormation(Formation formation) {

        return FormationResponse.builder()
                .id(formation.getId())
                .title(formation.getTitle())
                .description(formation.getDescription())
                .date(formation.getDate())
                .image(formation.getImage())
                .maxOfParticipants(formation.getMaxOfParticipants())
                .numberOfParticipants(formation.getNumberOfParticipants())
                .location(formation.getLocation())
                .formateur(formation.getFormateur())
                .price(formation.getPrice())
                .status(formation.isStatus())
                .createdAt(formation.getCreatedAt())
                .updatedAt(formation.getUpdatedAt())
                .build();

    }
}