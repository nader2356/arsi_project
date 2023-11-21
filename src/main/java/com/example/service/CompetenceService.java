package com.example.backendarsii.service;

import com.example.backendarsii.dto.CompetenceDTO;
import com.example.backendarsii.dto.requestDto.CompetenceRequest;
import com.example.backendarsii.entity.Competence;

import java.util.List;

public interface CompetenceService {
    void addCompetence (CompetenceRequest competenceRequest);
    List<CompetenceDTO> getAllCompetence();
    CompetenceDTO getCompetenceById(Long id);
    void deleteCompetence (Long id);
    void updateCompetence(Long id, CompetenceRequest competenceRequest);
}