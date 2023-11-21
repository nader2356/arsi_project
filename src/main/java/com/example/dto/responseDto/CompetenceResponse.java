package com.example.dto.responseDto;


import com.example.dto.entity.Competence;
import com.example.dto.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompetenceResponse {

    private Long id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

      public static CompetenceResponse makeCompetence(Competence competence){
        return CompetenceResponse.builder()
                .id(competence.getId())
                .name(competence.getName())
                .description(competence.getDescription())
                .createdAt(competence.getCreatedAt())
                .updatedAt(competence.getUpdatedAt()).build();
    }

}