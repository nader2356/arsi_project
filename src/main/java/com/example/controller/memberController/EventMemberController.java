package com.example.controller.memberController;


import com.example.dto.responseDto.EventResponse;
import com.example.service.EventService;
import com.example.util.Constants;
import com.example.util.enumData.EventType;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER+"/event")
@Api(tags = "(Member) Event Management ")
@CrossOrigin("*")
public class EventMemberController {

    private final EventService eventService;

  

//    @GetMapping(value = "{type}")
//    public ResponseEntity<List<EventResponse>> getAllEvent(@PathVariable EventType type) {
//        return ResponseEntity.ok(eventService.getAllEvent(type));
//
//    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvent(@RequestParam(required = false)  EventType type) {
        return ResponseEntity.ok(eventService.getAllEvent(type));

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {

        return ResponseEntity.ok(eventService.getEventById(id));
    }


}
