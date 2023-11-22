package com.example.service;

import com.example.dto.requestDto.UserEventRequest;
import com.example.dto.requestDto.UserFormationRequest;
import com.example.dto.responseDto.EventUserResponse;
import com.example.dto.responseDto.FormationUserResponse;
import com.example.dto.responseDto.UserEventResponse;
import com.example.dto.responseDto.UserFormationResponse;

import java.util.List;

public interface UserFormationService {

    void joinFormation (UserFormationRequest request);
    List<FormationUserResponse> getListOfFormationByUser(Long userId);
    List<UserFormationResponse> getListOfUserByFormation(Long formationId);
    void deleteUserFormation(Long id);


}