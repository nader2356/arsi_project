package com.example.controller.adminController;


import com.example.dto.requestDto.MediaRequest;
import com.example.dto.responseDto.MediaResponse;
import com.example.service.MediaService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN+"/media")
@Api(tags = "(Admin) Media Management ")
@CrossOrigin("*")
public class MediaController {


    private final MediaService mediaService;
    
    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<MediaResponse> getMediaById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @PostMapping
    public ResponseEntity<Object> addMedia(@RequestBody @Valid MediaRequest request) {
        mediaService.addMedia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !"));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteMedia(@PathVariable(name = "id") Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !"));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateMedia(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid MediaRequest request) {

        mediaService.updateMedia(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!!"));
    }
}
