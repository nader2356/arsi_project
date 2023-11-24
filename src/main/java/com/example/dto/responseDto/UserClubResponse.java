package com.example.dto.responseDto;


import com.example.entity.UserClub;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserClubResponse {

    private Integer id;
    private ClubResponse club;
    private Instant createdAt;
    private Instant updatedAt;

    public static UserClubResponse makeUserClubResponse(UserClub userClub) {
        return UserClubResponse.builder()
                .id(userClub.getId())
                .createdAt(userClub.getCreatedAt())
                .updatedAt(userClub.getUpdatedAt())
                .club(ClubResponse.makeClub( userClub.getClub()))
                .build();
    }

}
