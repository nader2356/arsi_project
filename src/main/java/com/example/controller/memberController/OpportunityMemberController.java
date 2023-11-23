package com.example.controller.memberController;


import com.example.dto.responseDto.OpportunityResponse;
import com.example.service.OpportunityService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER + "/opportunity")
@Api(tags = "(Member) Opportunity Management ")
@CrossOrigin("*")
public class OpportunityMemberController {

    private final OpportunityService opportunityService;


    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> getAllOpportunity (){
        return ResponseEntity.ok(opportunityService.getAllOpportunity());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<OpportunityResponse> getOpportunityById (@PathVariable Long id){

        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }



}