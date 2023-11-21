package com.example.backendarsii.service.serviceImpl;

import com.example.dto.CompetenceDTO;
import com.example.dto.requestDto.CompetenceRequest;
import com.example.entity.Competence;
import com.example.repository.CompetenceRepository;
import com.example.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;

    @Override
    public void addCompetence(CompetenceRequest competenceRequest) {

    }

    @Override
    public List<CompetenceDTO> getAllCompetence() {
        return null;
    }

    @Override
    public CompetenceDTO getCompetenceById(Long id) {
        return null;
    }

    @Override
    public void deleteCompetence(Long id) {

    }

    @Override
    public void updateCompetence(Long id, CompetenceRequest competenceRequest) {

    }
}