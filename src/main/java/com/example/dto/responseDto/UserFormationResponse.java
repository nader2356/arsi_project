package com.example.dto.responseDto;

import com.example.entity.UserEvent;
import com.example.entity.UserFormation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFormationResponse {


    private Long id;
    private UserResponse user;
    private Instant createdAt;
    private Instant updatedAt;

    public static UserFormationResponse makeUserFormationResponse(UserFormation userFormation){

        return UserFormationResponse.builder()
                .id(userFormation.getId())
                .createdAt(userFormation.getCreatedAt())
                .updatedAt(userFormation.getUpdatedAt())
                .user(UserResponse.makeUser(userFormation.getUser()))
                .build();
    }

}