package com.example.service.serviceImpl;


import com.example.dto.requestDto.UserEventRequest;
import com.example.dto.responseDto.EventUserResponse;
import com.example.dto.responseDto.UserEventResponse;
import com.example.entity.Event;
import com.example.entity.User;
import com.example.entity.UserEvent;
import com.example.exception.NotFoundException;
import com.example.repository.EventRepository;
import com.example.repository.UserEventRepository;
import com.example.repository.UserRepository;
import com.example.service.UserEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserEventServiceImpl implements UserEventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;
    @Override
    public void joinEvent(UserEventRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new NotFoundException(String.format("this userId [%s] is not exist", request.getUserId())));
        Event event = eventRepository.findById(request.getEventId()).orElseThrow(
                () -> new NotFoundException(String.format("this eventId [%s] is not exist", request.getEventId())));

        if (Objects.equals(event.getNumberOfParticipants(), event.getMaxOfParticipants())) {
            throw new RuntimeException("akahaw ba3 w rawa7 §§§§§§§§§§§µµµµµµµ*****");
        }
        event.setNumberOfParticipants(event.getNumberOfParticipants() + 1);

        userEventRepository.save(UserEvent.builder()
                .event(event)
                .user(user).build());
    }
    @Override
    public List<EventUserResponse> getListOfEventByUser(Long userId) {
        List<UserEvent> userEvents = userEventRepository.findAllByUserId(userId);
        List<EventUserResponse> eventUserResponses = new ArrayList<>();
        for (UserEvent userEvent : userEvents) {
            EventUserResponse eventUserResponse = EventUserResponse.makeEventUserResponse(userEvent);
            eventUserResponses.add(eventUserResponse);
        }
        return eventUserResponses;
    }
    @Override
    public List<UserEventResponse> getListOfUserByEvent(Long eventId) {
        List<UserEvent> userEvents = userEventRepository.findAllByEventId(eventId);
        List<UserEventResponse> userEventResponses = new ArrayList<>();
        for (UserEvent userEvent : userEvents) {
            UserEventResponse userEventResponse = UserEventResponse.makeUserEventResponse(userEvent);
            userEventResponses.add(userEventResponse);
        }
        return userEventResponses;
    }
    @Override
    public void deleteUserEvent(Long id) {
        UserEvent userEvent = userEventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this id [%s] is not exist", id)));
        Event event = eventRepository.findById(userEvent.getEvent().getId()).orElseThrow();
        event.setNumberOfParticipants(event.getNumberOfParticipants() - 1);
        eventRepository.save(event);
        userEventRepository.deleteById(id);
    }
}