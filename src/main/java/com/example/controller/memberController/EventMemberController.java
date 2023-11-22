package com.example.controller.memberController;

import com.example.dto.requestDto.EventRequest;
import com.example.dto.requestDto.UpdateEventRequest;
import com.example.dto.responseDto.EventResponse;
import com.example.service.EventService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<String> suggestEvent(@RequestBody @Valid EventRequest eventRequest){
        eventService.addEvent(eventRequest,false);
        return ResponseEntity.ok("save success !!");
    }
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvent(){

        return ResponseEntity.ok(eventService.getAllEvent());

    }
    @GetMapping(value = "{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id){

        return ResponseEntity.ok(eventService.getEventById(id));

    }


}
