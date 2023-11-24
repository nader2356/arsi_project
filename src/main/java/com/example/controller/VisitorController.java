package com.example.controller;

import com.example.dto.responseDto.EventResponse;
import com.example.dto.responseDto.PartnerResponse;
import com.example.dto.responseDto.UserResponse;
import com.example.service.EventService;
import com.example.service.PartnerService;
import com.example.service.UserService;
import com.example.util.Constants;
import com.example.util.enumData.EventType;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_ROOT + "/visitor")
@RequiredArgsConstructor
@Api(tags = "visitor ")
@CrossOrigin("*")
public class VisitorController {

    public final UserService userService;
    public final EventService eventService;
    public final PartnerService partnerService;

    @GetMapping(value = "allMember")
    public ResponseEntity<List<UserResponse>> getAllMember() {
        return ResponseEntity.ok(userService.getAllMember());
    }
    @GetMapping(value = "allEvent/{type}")
    public ResponseEntity<List<EventResponse>> getAllEvent(@PathVariable EventType type) {

    	 return ResponseEntity.ok(eventService.getAllEvent(type));

    }
   
    @GetMapping("allPartner")
    public ResponseEntity<List<PartnerResponse>> getAllPartner() {
        return ResponseEntity.ok(partnerService.getAllPartner());
    }


}
