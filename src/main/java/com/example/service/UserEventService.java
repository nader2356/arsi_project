package com.example.service;

import com.example.dto.requestDto.UserEventRequest;
import com.example.dto.responseDto.EventUserResponse;
import com.example.dto.responseDto.UserEventResponse;

import java.util.List;

public interface UserEventService {
    void joinEvent(UserEventRequest request);

    List<EventUserResponse> getListOfEventByUser(Long userId);

    List<UserEventResponse> getListOfUserByEvent(Long eventId);

    void deleteUserEvent(Long id);

    boolean checkJoinedEvent(UserEventRequest request);
}