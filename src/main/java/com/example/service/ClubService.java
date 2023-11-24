package com.example.service;

import com.example.dto.requestDto.ClubRequest;
import com.example.dto.responseDto.ClubResponse;

import java.util.List;

public interface ClubService {
    List<ClubResponse> getAllClub();

    void deleteClub(Integer id);

    void createClub(ClubRequest clubRequest);

    ClubResponse updateClub(Integer id, ClubRequest clubRequest);

    ClubResponse getClubById(Integer clubId);
}
