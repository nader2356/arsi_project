package com.example.dto.requestDto;


import com.example.util.enumData.EventType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {

    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "description is required")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    private Long maxOfParticipants;
    private String formateur;
    private Long price;
    @NotBlank(message = "location is required")
    private String location;
    private String image;
    @Enumerated(EnumType.STRING)
    private EventType type;
    private Long partnerId;
    private boolean status;
    private boolean isActivity;

}