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
public class EventUserResponse {

    private Long id;
    private EventResponse event;
    private Instant createdAt;
    private Instant updatedAt;

    public static EventUserResponse makeEventUserResponse(UserEvent userEvent){

        return EventUserResponse.builder()
                .id(userEvent.getId())
                .createdAt(userEvent.getCreatedAt())
                .updatedAt(userEvent.getUpdatedAt())
                .event(EventResponse.makeEvent(userEvent.getEvent()))
                .build();
    }

}