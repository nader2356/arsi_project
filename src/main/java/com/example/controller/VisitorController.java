package com.example.controller;


import com.example.util.Constants;
import com.example.util.EmailUtil;
import com.example.util.enumData.EventType;
import com.example.dto.requestDto.EmailForm;
import com.example.dto.requestDto.NewsletterRequest;
import com.example.dto.responseDto.*;
import com.example.service.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.APP_ROOT + "/visitor")
@RequiredArgsConstructor
@Api(tags = "visitor ")
@CrossOrigin("*")
public class VisitorController {
    public final UserService userService;
    public final EventService eventService;
    public final PartnerService partnerService;
    public final OpportunityService opportunityService;
    public final ClubService clubService;
    private final MediaService mediaService;
    private final NewsletterService newsletterService;
    private final EmailUtil emailUtil;


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

    @GetMapping("allOportunity")
    public ResponseEntity<List<OpportunityResponse>> getAllOpportunity() {
        return ResponseEntity.ok(opportunityService.getAllOpportunity());
    }

    @GetMapping(value = "event/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    @GetMapping("/allClub")
    public ResponseEntity<List<ClubResponse>> getAllClub() {
        List<ClubResponse> clubs = clubService.getAllClub();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping(value = "allMedia")
    public ResponseEntity<List<MediaResponse>> getAllMedia() {

        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @GetMapping(value = "media/{id}")
    public ResponseEntity<MediaResponse> getMediaById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @PostMapping(value = "sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailForm form) {

        emailUtil.sendEmail("attiahatem.tn@gmail.com", form.getFrom(), form.getSubject(), form.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "OK !!!!!!"));
    }

    @PostMapping(value = "newsletter")
    public ResponseEntity<Object> createNewsletter(@RequestBody @Valid NewsletterRequest newsletterRequest) {
        if (newsletterRequest != null) {
            newsletterService.createNewsletter(newsletterRequest);
            return
                    ResponseEntity.status(HttpStatus.CREATED).body(
                            Collections.singletonMap("message", "newsletter created successfully"));
        } else {
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("message","Invalid newsletter data"));
        }
    }
    @GetMapping(value = "qrcode/{id}")
    public ResponseEntity<Object> verificationUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getMemberById(id));
    }
}