package com.example.controller.adminController;

import com.example.dto.requestDto.UserEventRequest;
import com.example.dto.responseDto.EventUserResponse;
import com.example.dto.responseDto.UserEventResponse;
import com.example.service.UserEventService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN+"/userEvent")
@Api(tags = "(Admin) User's Event management ")
@CrossOrigin("*")
public class UserEventAdminController {

    private final UserEventService userEventService;

    @PostMapping
    public ResponseEntity<String> joinEvent(@RequestBody @Valid UserEventRequest request){
        userEventService.joinEvent(request);
        return ResponseEntity.ok("join success !!!");
    }

    @GetMapping(value = "/users/{eventId}")
    public ResponseEntity<List<UserEventResponse>> getListOfUserByEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(userEventService.getListOfUserByEvent(eventId));
    }
    @GetMapping(value = "/events/{userId}")
    public ResponseEntity<List<EventUserResponse>> getListOfEventByUser(@PathVariable Long userId){
        return ResponseEntity.ok(userEventService.getListOfEventByUser(userId));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteUserEvent(@PathVariable Long id){
        userEventService.deleteUserEvent(id);
        return ResponseEntity.ok("delete success !!!");
    }

}