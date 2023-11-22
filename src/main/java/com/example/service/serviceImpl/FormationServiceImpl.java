package com.example.service.serviceImpl;

import com.example.dto.requestDto.FormationRequest;
import com.example.dto.requestDto.UpdateFormationRequest;
import com.example.dto.responseDto.FormationResponse;
import com.example.entity.Formation;
import com.example.exception.NotFoundException;
import com.example.repository.FormationRepository;
import com.example.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    @Override
    public void addFormation(FormationRequest formationRequest, boolean status) {
       formationRepository.save(Formation.builder()
               .title(formationRequest.getTitle())
               .description(formationRequest.getDescription())
               .date(formationRequest.getDate())
               .image(formationRequest.getImage())
               .maxOfParticipants(formationRequest.getMaxOfParticipants())
               .numberOfParticipants(0L)
               .location(formationRequest.getLocation())
               .formateur(formationRequest.getFormateur())
               .price(formationRequest.getPrice())
               .status(status).build());
    }

    @Override
    public List<FormationResponse> getAllFormation() {

        List<Formation> formations = formationRepository.findAllFormation();
        List<FormationResponse> formationResponses = new ArrayList<>();
        for (Formation formation: formations) {
            FormationResponse formationResponse = FormationResponse.makeFormation(formation);
            formationResponses.add(formationResponse);
        }
        return formationResponses;
    }
    @Override
    public List<FormationResponse> getAllSuggestFormation() {

        List<Formation> formations = formationRepository.findAllSuggestFormation();
        List<FormationResponse> formationResponses = new ArrayList<>();
        for (Formation formation: formations) {
            FormationResponse formationResponse = FormationResponse.makeFormation(formation);
            formationResponses.add(formationResponse);
        }
        return formationResponses;
    }

    @Override
    public FormationResponse getFormationById(Long id) {
        Formation formation = formationRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this Id[%s] is not exist ",id)));
        return FormationResponse.makeFormation(formation);
    }

    @Override
    public void updateFormation(Long id, UpdateFormationRequest updateFormation) {

        Formation formation = formationRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this Id[%s] is not exist ",id)));
        formation.setTitle(updateFormation.getTitle());
        formation.setDescription(updateFormation.getDescription());
        formation.setDate(updateFormation.getDate());
        formation.setImage(updateFormation.getImage());
        formation.setNumberOfParticipants(formation.getNumberOfParticipants());
        formation.setLocation(updateFormation.getLocation());
        formation.setFormateur(updateFormation.getFormateur());
        formation.setPrice(updateFormation.getPrice());
        formation.setStatus(updateFormation.isStatus());

        formationRepository.save(formation);


    }

    @Override
    public void deleteFormation(Long id) {

        if(!formationRepository.existsById(id)){
            throw new NotFoundException(String.format("this Id[%s] is not exist ",id));
        }
        formationRepository.deleteById(id);

    }
}