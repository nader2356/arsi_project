package com.example.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFormationRequest {


    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "description is required")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    @NotBlank(message = "image is required")
    private String image;
    @NotNull(message = "maxOfParticipants is required")
    private Long maxOfParticipants;
    @NotBlank(message = "location is required")
    private String location;
    @NotBlank(message = "formateur is required")
    private String formateur;
    @NotNull(message = "price is required")
    private Long price;
    private boolean status;

}
