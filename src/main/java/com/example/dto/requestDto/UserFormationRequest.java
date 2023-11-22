package com.example.dto.requestDto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFormationRequest {
    @NotNull(message = "Formation id is required")
    private Long formationId;
    @NotNull(message = "user id is required")
    private Long userId;
}