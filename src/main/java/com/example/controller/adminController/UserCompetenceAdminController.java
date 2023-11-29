package com.example.controller.adminController;

import com.example.dto.requestDto.UserCompetenceRequest;
import com.example.dto.responseDto.UserCompetenceResponse;
import com.example.dto.responseDto.UserResponse;
import com.example.service.UserCompetenceService;
import com.example.service.UserService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/userCompetence")
@Api(tags = "(Admin) User's Competence management ")
@CrossOrigin("*")
public class UserCompetenceAdminController {
    private final UserCompetenceService userCompetenceService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<Object> addUserCompetence(@RequestBody @Valid UserCompetenceRequest request) {
        userCompetenceService.addUserCompetence(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !!"));
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<List<UserResponse>> getAllUserByCompetence(@PathVariable Long id) {
        return ResponseEntity.ok(userCompetenceService.getAllUserByCompetence(id));
    }
    @GetMapping(value = "competences")
    public ResponseEntity<List<UserCompetenceResponse>> getAllCompetencesByMe() {
        UserResponse userDto = userService.getConnectedUser();
        return ResponseEntity.ok(userCompetenceService.getAllCompetenceByUser(userDto.getId()));
    }
    @GetMapping(value = "competences/{id}")
    public ResponseEntity<List<UserCompetenceResponse>> getAllCompetencesByUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userCompetenceService.getAllCompetenceByUser(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUserCompetence(
            @PathVariable Long id,
            @RequestBody @Valid UserCompetenceRequest request
    ) {
        userCompetenceService.updateUserCompetence(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> updateUserCompetence(@PathVariable Long id) {
        userCompetenceService.deleteUserCompetence(id);


        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !!"));
    }

}