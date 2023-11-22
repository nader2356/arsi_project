package com.example.service.serviceImpl;

import com.example.dto.requestDto.EventRequest;
import com.example.dto.requestDto.UpdateEventRequest;
import com.example.dto.responseDto.EventResponse;
import com.example.entity.Event;
import com.example.entity.Partner;
import com.example.exception.NotFoundException;
import com.example.repository.EventRepository;
import com.example.repository.PartnerRepository;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final PartnerRepository partnerRepository;
    private final EventRepository eventRepository;
    @Override
    public void addEvent(EventRequest eventRequest,boolean status) {
        Partner partner = null;
        if (eventRequest.getPartnerId()!=null){
            partner = partnerRepository.findById(eventRequest.getPartnerId()).orElseThrow(
                    ()-> new NotFoundException(String.format("this partnerId[%s] is not exist",eventRequest.getPartnerId())));
        }
        eventRepository.save(Event.builder()
                        .title(eventRequest.getTitle())
                        .description(eventRequest.getDescription())
                        .date(eventRequest.getDate())
                        .image(eventRequest.getImage())
                        .location(eventRequest.getLocation())
                        .type(eventRequest.getType())
                        .partner(partner)
                        .numberOfParticipants(0L)
                        .status(status).build());

    }

    @Override
    public List<EventResponse> getAllEvent() {
        List<Event> events = eventRepository.findAllEvent();
        List<EventResponse> eventResponses = new ArrayList<>();
        for (Event event:events) {
            EventResponse eventResponse = EventResponse.makeEvent(event);
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    }

    @Override
    public List<EventResponse> getAllSuggestEvent() {
        List<Event> events = eventRepository.findAllSuggestEvent();
        List<EventResponse> eventResponses = new ArrayList<>();
        for (Event event:events) {
            EventResponse eventResponse = EventResponse.makeEvent(event);
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this id [%s] is not exist",id)));
        return EventResponse.makeEvent(event);
    }

    @Override
    public void updateEvent(Long id, UpdateEventRequest updateEventRequest) {

        Partner partner = null;
        if (updateEventRequest.getPartnerId()!=null){
            partner = partnerRepository.findById(updateEventRequest.getPartnerId()).orElseThrow(
                    ()-> new NotFoundException(String.format("this partnerId[%s] is not exist",updateEventRequest.getPartnerId())));
        }

        Event event = eventRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("this Id [%s] is not exist",id)));
        event.setTitle(updateEventRequest.getTitle());
        event.setDescription(updateEventRequest.getDescription());
        event.setDate(updateEventRequest.getDate());
        event.setImage(updateEventRequest.getImage());
        event.setLocation(updateEventRequest.getLocation());
        event.setType(updateEventRequest.getType());
        event.setPartner(partner);
        event.setStatus(event.isStatus());

        eventRepository.save(event);

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);

    }
}