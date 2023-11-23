package com.example.dto.requestDto;

import com.example.util.enumData.OpportunityType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityRequest {


    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private OpportunityType type;
    private String company;

}