package com.example.dto.requestDto;


import com.example.util.enumData.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

	 @NotNull(message = "userId is required")	   
	    private UUID userId;
	    @Enumerated(EnumType.STRING)
	    private Platform platform;
	    @URL(message = "this URL is not valid")
	    private String url;
}
