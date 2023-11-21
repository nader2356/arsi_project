package com.example.dto.responseDto;

import com.example.entity.Competence;

import com.example.entity.UserCompetence;
import com.example.util.enumData.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCompetenceResponse {


    private Long id;
    @Enumerated(EnumType.STRING)
    private Level level;
    private CompetenceResponse competence;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean deleted = Boolean.FALSE;


    public static UserCompetenceResponse makeUserCompetence(UserCompetence userCompetence){
        return UserCompetenceResponse.builder()
                .id(userCompetence.getId())
                .level(userCompetence.getLevel())
                .createdAt(userCompetence.getCreatedAt())
                .updatedAt(userCompetence.getUpdatedAt())
                .competence(CompetenceResponse.makeCompetence(userCompetence.getCompetence()))
                .build();

    }

}