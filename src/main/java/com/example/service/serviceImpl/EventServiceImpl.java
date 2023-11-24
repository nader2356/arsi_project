package com.example.service.serviceImpl;

import com.example.config.UtilsConfiguration;
import com.example.dto.requestDto.EventRequest;
import com.example.dto.requestDto.UpdateEventRequest;
import com.example.dto.responseDto.EventResponse;
import com.example.entity.Event;
import com.example.entity.Partner;
import com.example.exception.NotFoundException;
import com.example.repository.EventRepository;
import com.example.repository.PartnerRepository;
import com.example.service.EventService;
import com.example.util.FileStorageService;
import com.example.util.enumData.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final PartnerRepository partnerRepository;
    private final EventRepository eventRepository;
    @Autowired
    private FileStorageService fileStorageService;

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
                .type(eventRequest.getType())
                .partner(partner)
                .numberOfParticipants(0L)
                .status(true).build());

    }

    @Override
    public List<EventResponse> getAllEvent(EventType type) {
        List<Event> events = eventRepository.findAllEvent(type);
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
        Partner partner = null;
        if (updateEventRequest.getPartnerId() != null) {
            partner = partnerRepository.findById(updateEventRequest.getPartnerId()).orElseThrow(
                    () -> new NotFoundException(String.format("this partnerId[%s] is not exist", updateEventRequest.getPartnerId())));
        }
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this Id [%s] is not exist", id)));
        event.setTitle(updateEventRequest.getTitle());
        event.setDescription(updateEventRequest.getDescription());
        event.setDate(updateEventRequest.getDate());
        event.setMaxOfParticipants(updateEventRequest.getMaxOfParticipants());
        event.setPrice(updateEventRequest.getPrice());
        event.setFormateur(updateEventRequest.getFormateur());
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
    @Override
    public void uploadImage(MultipartFile file, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("event is not exist"));
        if (UtilsConfiguration.isImage(Objects.requireNonNull(file.getContentType()))){
            fileStorageService.storeFile(file, "EVENT_IMG");
            event.setImage(file.getOriginalFilename());
            eventRepository.save(event);
        }else{
            throw new RuntimeException("mahiyech image****************");
        }
    }
    @Override
    public Resource serveImage(String fileName) {
        fileName = "EVENT_IMG/"+fileName;
        return fileStorageService.loadFileAsResource(fileName);
    }
}