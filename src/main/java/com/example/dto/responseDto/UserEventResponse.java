package com.example.dto.responseDto;


import com.example.entity.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEventResponse {

    private Long id;
    private UserResponse user;
    private Instant createdAt;
    private Instant updatedAt;

    public static UserEventResponse makeUserEventResponse(UserEvent userEvent){

        return UserEventResponse.builder()
                .id(userEvent.getId())
                .createdAt(userEvent.getCreatedAt())
                .updatedAt(userEvent.getUpdatedAt())
                .user(UserResponse.makeUser(userEvent.getUser()))
                .build();
    }

}