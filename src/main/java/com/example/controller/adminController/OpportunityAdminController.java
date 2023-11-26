package com.example.controller.adminController;

import com.example.dto.requestDto.OpportunityRequest;
import com.example.dto.responseDto.OpportunityResponse;
import com.example.service.OpportunityService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/opportunity")
@Api(tags = "(Admin) Opportunity Management ")
@CrossOrigin("*")
public class OpportunityAdminController {
    private final OpportunityService opportunityService;

    @PostMapping
    public ResponseEntity<Object> createOpportunity (@RequestBody OpportunityRequest request){
        opportunityService.createOpportunity(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !!!"));
    }
    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> getAllOpportunity (){
        return ResponseEntity.ok(opportunityService.getAllOpportunity());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<OpportunityResponse> getOpportunityById (@PathVariable Long id){
        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> UpdateOpportunity (
            @RequestBody OpportunityRequest request,
            @PathVariable Long id){

        opportunityService.updateOpportunity(request,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!!!"));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteOpportunity (@PathVariable Long id){

        opportunityService.deleteOpportunity(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !!!!"));
    }
    
    }