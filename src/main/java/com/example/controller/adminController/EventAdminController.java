package com.example.controller.adminController;

import com.example.dto.requestDto.EventRequest;
import com.example.dto.requestDto.UpdateEventRequest;
import com.example.dto.responseDto.EventResponse;
import com.example.service.EventService;
import com.example.util.Constants;
import com.example.util.enumData.EventType;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/event")
@Api(tags = "(Admin) Event Management ")
@CrossOrigin("*")
public class EventAdminController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventRequest eventRequest) {
    	 eventService.addEvent(eventRequest);
        return ResponseEntity.ok("save success !!");
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvent(@RequestParam  EventType type) {
        return ResponseEntity.ok(eventService.getAllEvent(type));
    }

    @GetMapping(value = "/activity")
    public ResponseEntity<List<EventResponse>> getAllActivity() {
        return ResponseEntity.ok(eventService.getAllActivity());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<String> updateEvent(
            @RequestBody @Valid UpdateEventRequest request,
            @PathVariable Long id) {
        eventService.updateEvent(id, request);
        return ResponseEntity.ok("update success !!");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("delete success !!");
    }
    
   

}