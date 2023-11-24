package com.example.dto.requestDto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClubRequest {

    @NotNull(message = "Club id is required")
    private Integer clubId;
    @NotNull(message = "user id is required")
    private Integer userId;
}
