package com.example.controller.memberController;

import com.example.dto.requestDto.FormationRequest;
import com.example.dto.responseDto.FormationResponse;
import com.example.service.FormationService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER + "/formation")
@Api(tags = "(Member) formation Management ")
@CrossOrigin("*")
public class FormationMemberController {

    private final FormationService formationService;

    @PostMapping
    public ResponseEntity<String> suggestFormation(@RequestBody @Valid FormationRequest formationRequest) {
        formationService.addFormation(formationRequest, false);
        return ResponseEntity.ok("save success !!");
    }

    @GetMapping
    public ResponseEntity<List<FormationResponse>> getAllFormation() {
        return ResponseEntity.ok(formationService.getAllFormation());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FormationResponse> getFormationById(@PathVariable Long id) {
        return ResponseEntity.ok(formationService.getFormationById(id));
    }
}
