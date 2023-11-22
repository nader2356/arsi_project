package com.example.dto.responseDto;


import com.example.entity.Partner;
import com.example.util.enumData.PartnerType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PartnerResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String contact;
    private PartnerType type;
    private String image;
    private Instant createdAt;
    private Instant updatedAt;


    public static PartnerResponse makePartner(Partner partner){
        return PartnerResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .description(partner.getDescription())
                .address(partner.getAddress())
                .contact(partner.getContact())
                .type(partner.getType())
                .image(partner.getImage())
                .createdAt(partner.getCreatedAt())
                .updatedAt(partner.getUpdatedAt()).build();
    }
}