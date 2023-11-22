package com.example.controller.memberController;

import com.example.dto.responseDto.CompetenceResponse;
import com.example.service.CompetenceService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER+"/competence")
@Api(tags = "(Member) Competence Management  ")

@CrossOrigin("*")
public class CompetenceMemberController {

    private final CompetenceService competenceService;

    @GetMapping
    public ResponseEntity<List<CompetenceResponse>> getAllCompetence() {
        return ResponseEntity.ok(competenceService.getAllCompetence());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CompetenceResponse> getCompetenceById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(competenceService.getCompetenceById(id));
    }

}