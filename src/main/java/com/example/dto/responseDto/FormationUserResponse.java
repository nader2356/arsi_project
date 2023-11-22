package com.example.dto.responseDto;

import com.example.entity.UserFormation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormationUserResponse {

    private Long id;
    private FormationResponse formation;
    private Instant createdAt;
    private Instant updatedAt;

    public static FormationUserResponse makeFormationUserResponse(UserFormation userFormation){

        return FormationUserResponse.builder()
                .id(userFormation.getId())
                .createdAt(userFormation.getCreatedAt())
                .updatedAt(userFormation.getUpdatedAt())
                .formation(FormationResponse.makeFormation(userFormation.getFormation()))
                .build();
    }
}