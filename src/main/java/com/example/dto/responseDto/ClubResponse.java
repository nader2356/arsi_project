package com.example.dto.responseDto;

import com.example.entity.Club;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Instant;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class ClubResponse {
    private Integer id;
    private String name;
    private String logo;
    private String location;
    private String description;
    private String contact;
    @Enumerated(EnumType.STRING)
    private boolean status;
    private Instant createdAt;
    private Instant updatedAt;
    public static ClubResponse makeClub(Club club)
    {
        return  ClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .logo(club.getLogo())
                .location(club.getLocation())
                .description(club.getDescription())
                .contact(club.getContact())
                .status(club.isStatus())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .build();
    }
}