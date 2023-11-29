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
import com.example.util.enumData.EventType;
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
    public void addEvent(EventRequest eventRequest) {
        Partner partner = null;
        if (eventRequest.getPartnerId() != null) {
            partner = partnerRepository.findById(eventRequest.getPartnerId()).orElseThrow(
                    () -> new NotFoundException(String.format("this partnerId[%s] is not exist", eventRequest.getPartnerId())));
        }
        eventRepository.save(Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .date(eventRequest.getDate())
                .maxOfParticipants(eventRequest.getMaxOfParticipants())
                .formateur(eventRequest.getFormateur())
                .price(eventRequest.getPrice())
                .location(eventRequest.getLocation())
                .urlFacebook(eventRequest.getUrlFacebook())
                .type(eventRequest.getType())
                .partner(partner)
                .image(eventRequest.getImage())
                .numberOfParticipants(0L)
                .status(true).build());
    }
    @Override
    public List<EventResponse> getAllEvent(EventType type) {
        System.out.println(type + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        List<Event> events = new ArrayList<>();
        if (type == null) {
            events = eventRepository.findAllEvent();
        } else {
            events = eventRepository.findAllEvent(type);
        }
        List<EventResponse> eventResponses = new ArrayList<>();
        for (Event event : events) {
            EventResponse eventResponse = EventResponse.makeEvent(event);
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    }
    @Override
    public List<EventResponse> getAllActivity() {
        List<Event> events = eventRepository.findAllActivity();
        List<EventResponse> eventResponses = new ArrayList<>();
        for (Event event : events) {
            EventResponse eventResponse = EventResponse.makeEvent(event);
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    }
    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this id [%s] is not exist", id)));
        return EventResponse.makeEvent(event);
    }
    @Override
    public void updateEvent(Long id, UpdateEventRequest updateEventRequest) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this Id [%s] is not exist", id)));
        if (updateEventRequest.getTitle() != null) {
            event.setTitle(updateEventRequest.getTitle());
        }
        if (updateEventRequest.getDescription() != null) {
            event.setDescription(updateEventRequest.getDescription());
        }
        if (updateEventRequest.getDate() != null) {
            event.setDate(updateEventRequest.getDate());
        }
        if (updateEventRequest.getUrlFacebook() != null) {
            event.setUrlFacebook(updateEventRequest.getUrlFacebook());
        }
        if (updateEventRequest.getMaxOfParticipants() != 0) {
            event.setMaxOfParticipants(updateEventRequest.getMaxOfParticipants());
        }
        System.out.println("----------------------------------------"+updateEventRequest.getPrice());
        if (updateEventRequest.getPrice() != 0) {
            event.setPrice(updateEventRequest.getPrice());
        }
        if (updateEventRequest.getFormateur() != null) {
            event.setFormateur(updateEventRequest.getFormateur());
        }
        if (updateEventRequest.getLocation() != null) {
            event.setLocation(updateEventRequest.getLocation());
        }
        if (updateEventRequest.getType() != null) {
            event.setType(updateEventRequest.getType());
        }
        if (updateEventRequest.getPartnerId() != null) {
            Partner partner = null;
                partner = partnerRepository.findById(updateEventRequest.getPartnerId()).orElseThrow(
                        () -> new NotFoundException(String.format("this partnerId[%s] is not exist", updateEventRequest.getPartnerId())));
            event.setPartner(partner);
        }
        if (updateEventRequest.getImage() != null) {
            event.setImage(updateEventRequest.getImage());
        }
        if (!updateEventRequest.isStatus()) {
            event.setStatus(event.isStatus());
        }
        event.setActivity(updateEventRequest.isActivity());
        eventRepository.save(event);
    }
    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}