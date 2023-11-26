package com.example.controller.adminController;

import com.example.dto.requestDto.CompetenceRequest;
import com.example.dto.responseDto.CompetenceResponse;
import com.example.service.CompetenceService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/competence")
@Api(tags = "(Admin) Competence Management ")
@CrossOrigin("*")
public class CompetenceAdminController {
    private final CompetenceService competenceService;
    @GetMapping
    public ResponseEntity<List<CompetenceResponse>> getAllCompetence() {
        return ResponseEntity.ok(competenceService.getAllCompetence());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<CompetenceResponse> getCompetenceById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(competenceService.getCompetenceById(id));
    }

    @PostMapping
    public ResponseEntity<Object> addCompetence(@RequestBody @Valid CompetenceRequest request) {
        competenceService.addCompetence(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !"));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteCompetence(@PathVariable(name = "id") Long id) {
        competenceService.deleteCompetence(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !"));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateCompetence(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid CompetenceRequest request) {

        competenceService.updateCompetence(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!!"));
    }



}