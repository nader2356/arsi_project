package com.example.dto.responseDto;

import com.example.entity.Category;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;


@Data
@Builder
public class CategoryResponse {

	 private Long id;
	    private String name;
	    private String description;
	    private List<CompetenceResponse> competences;
	    private Instant createdAt;
	    private Instant updatedAt;

	    public static CategoryResponse makeCategory(Category category) {
	        return CategoryResponse.builder()
	                .id(category.getId())
	                .name(category.getName())
	                .description(category.getDescription())
	                .competences(CompetenceResponse.makeCompetences(category.getCompetences()))
	                .createdAt(category.getCreatedAt())
	                .updatedAt(category.getUpdatedAt()).build();
	    }
}