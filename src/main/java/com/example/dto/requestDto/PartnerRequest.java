package com.example.dto.requestDto;

import com.example.util.enumData.PartnerType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerRequest {


    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "Contact is required")
    private String contact;
    @Enumerated(EnumType.STRING)
    private PartnerType type;
    private String image;


}