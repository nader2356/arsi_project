package com.example.service;

import com.example.dto.requestDto.UserCompetenceRequest;
import com.example.dto.responseDto.CompetenceResponse;
import com.example.dto.responseDto.UserCompetenceResponse;
import com.example.dto.responseDto.UserDto;

import java.util.List;

public interface UserCompetenceService {

    void addUserCompetence(UserCompetenceRequest userCompetenceRequest);
    void updateUserCompetence(Long id, UserCompetenceRequest userCompetenceRequest);
    List<UserCompetenceResponse> getAllCompetenceByUser(Long id);
    List<UserDto> getAllUserByCompetence(Long id);
    void deleteUserCompetence(Long id);

}