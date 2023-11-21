package com.example.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {


    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
}