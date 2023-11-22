package com.example.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventRequest {
    @NotNull(message = "Event id is required")
    private Long eventId;
    @NotNull(message = "user id is required")
    private Long userId;
}
