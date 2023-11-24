package com.example.service;


import com.example.dto.requestDto.CompetenceRequest;
import com.example.dto.responseDto.CompetenceResponse;


import java.util.List;

public interface CompetenceService {
    void addCompetence (CompetenceRequest competenceRequest);
    List<CompetenceResponse> getAllCompetence();
    CompetenceResponse getCompetenceById(Long id);
    void deleteCompetence (Long id);
    void updateCompetence(Long id, CompetenceRequest competenceRequest);
}