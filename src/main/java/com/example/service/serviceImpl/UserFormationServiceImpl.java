package com.example.service.serviceImpl;


import com.example.dto.requestDto.UserFormationRequest;
import com.example.dto.responseDto.FormationUserResponse;
import com.example.dto.responseDto.UserFormationResponse;
import com.example.entity.*;
import com.example.exception.NotFoundException;
import com.example.repository.FormationRepository;
import com.example.repository.UserFormationRepository;
import com.example.repository.UserRepository;
import com.example.service.UserFormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserFormationServiceImpl implements UserFormationService {

    private final UserRepository userRepository;
    private final FormationRepository formationRepository;
    private final UserFormationRepository userFormationRepository;
    @Override
    public void joinFormation(UserFormationRequest request) {

        Formation formation = formationRepository.findById(request.getFormationId()).orElseThrow(
                ()-> new NotFoundException(String.format("this formationId [%s] is not exist",request.getFormationId())));

        if (Objects.equals(formation.getNumberOfParticipants(), formation.getMaxOfParticipants())){
            throw new RuntimeException("akahaw ba3 w rawa7 §§§§§§§§§§§µµµµµµµ*****");
        }

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new NotFoundException(String.format("this userId [%s] is not exist",request.getUserId())));



        formation.setNumberOfParticipants(formation.getNumberOfParticipants()+1);

        userFormationRepository.save(UserFormation.builder()
                .formation(formation)
                .user(user).build());
    }

    @Override
    public List<FormationUserResponse> getListOfFormationByUser(Long userId) {
        List<UserFormation> userFormations = userFormationRepository.findAllByUserId(userId);
        List<FormationUserResponse> formationUserResponses = new ArrayList<>();

        for (UserFormation userFormation: userFormations) {
            FormationUserResponse formationUserResponse = FormationUserResponse.makeFormationUserResponse(userFormation);
            formationUserResponses.add(formationUserResponse);
        }
        return formationUserResponses;
    }

    @Override
    public List<UserFormationResponse> getListOfUserByFormation(Long formationId) {
        List<UserFormation> userFormations = userFormationRepository.findAllByFormationId(formationId);
        List<UserFormationResponse> userFormationResponses = new ArrayList<>();

        for (UserFormation userFormation: userFormations) {
            UserFormationResponse userFormationResponse = UserFormationResponse.makeUserFormationResponse(userFormation);
            userFormationResponses.add(userFormationResponse);
        }
        return userFormationResponses;
    }

    @Override
    public void deleteUserFormation(Long id) {

        UserFormation userFormation = userFormationRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this id [%s] is not exist",id)));
        Formation formation = formationRepository.findById(userFormation.getFormation().getId()).orElseThrow();
        formation.setNumberOfParticipants(formation.getNumberOfParticipants()-1);
        formationRepository.save(formation);
        userFormationRepository.deleteById(id);

    }
}