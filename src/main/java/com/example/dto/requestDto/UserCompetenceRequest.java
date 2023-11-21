package com.example.dto.requestDto;

import com.example.util.enumData.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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