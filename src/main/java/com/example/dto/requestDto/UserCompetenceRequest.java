package com.example.dto.requestDto;

import com.example.util.enumData.Level;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCompetenceRequest {


    @NotNull(message = "competenceId is required")
    private Long competenceId;
    @Enumerated(EnumType.STRING)
    private Level level;

}