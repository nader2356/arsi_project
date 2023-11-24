package com.example.service;

import com.example.dto.requestDto.OpportunityRequest;
import com.example.dto.responseDto.OpportunityResponse;


import java.util.List;

public interface OpportunityService {

    void createOpportunity(OpportunityRequest request);
    OpportunityResponse getOpportunityById (Long id);
    List<OpportunityResponse> getAllOpportunity();
    void updateOpportunity(OpportunityRequest request,Long id);
    void deleteOpportunity(Long id);


}
