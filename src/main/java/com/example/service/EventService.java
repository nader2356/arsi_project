package com.example.service;

import com.example.dto.requestDto.EventRequest;
import com.example.dto.requestDto.UpdateEventRequest;
import com.example.dto.responseDto.EventResponse;
import com.example.util.enumData.EventType;

import java.util.List;



public interface EventService {

    void addEvent(EventRequest eventRequest);


    List<EventResponse> getAllEvent(EventType type);
    List<EventResponse> getAllActivity();



    EventResponse getEventById(Long id);

    void updateEvent(Long id, UpdateEventRequest updateEventRequest);
    void deleteEvent(Long id);

}