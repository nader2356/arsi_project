package com.example.dto.responseDto;

import com.example.entity.Opportunity;
import com.example.util.enumData.OpportunityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityResponse {


    private Long id;
    private String title;
    private String description;
    private OpportunityType type;
    private String company;
    private String image;
    private Instant createdAt;
    private Instant updatedAt;

    public static OpportunityResponse makeOpportunity(Opportunity opportunity){
        return OpportunityResponse.builder()
                .id(opportunity.getId())
                .title(opportunity.getTitle())
                .description(opportunity.getDescription())
                .type(opportunity.getType())
                .company(opportunity.getCompany())
                .image(opportunity.getImage())
                .createdAt(opportunity.getCreatedAt())
                .updatedAt(opportunity.getUpdatedAt()).build();
    }

}