package com.example.service;

import com.example.dto.requestDto.FormationRequest;
import com.example.dto.requestDto.UpdateFormationRequest;
import com.example.dto.responseDto.FormationResponse;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FormationService {

    void addFormation(FormationRequest formationRequest, boolean status);
    List<FormationResponse> getAllFormation();
    List<FormationResponse> getAllSuggestFormation();
    FormationResponse getFormationById(Long id);
    void updateFormation (Long id, UpdateFormationRequest updateFormation);
    void deleteFormation(Long id);
    void uploadImage(MultipartFile file, Long id);
    Resource serveImage(String fileName);

}