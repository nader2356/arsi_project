package com.example.service.serviceImpl;

import com.example.dto.requestDto.ClubRequest;
import com.example.dto.responseDto.ClubResponse;
import com.example.entity.Club;
import com.example.exception.NotFoundException;
import com.example.repository.ClubRepository;
import com.example.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Override
    public List<ClubResponse> getAllClub() {
        List<Club> clubs = clubRepository.findAllClub();
        List<ClubResponse> clubResponses = new ArrayList<>();
        for(Club club : clubs) {
            ClubResponse clubDto = ClubResponse.makeClub(club);
            clubResponses.add(clubDto);
        }
        return  clubResponses;
    }
    @Override
    public void deleteClub(Integer id) {
        clubRepository.deleteById(id);
    }

    @Override
    public void createClub(ClubRequest clubRequest) {
        Club club = Club.builder()
                .name(clubRequest.getName())
                .logo(clubRequest.getLogo())
                .location(clubRequest.getLocation())
                .description(clubRequest.getDescription())
                .contact(clubRequest.getContact())
                .status(clubRequest.isStatus())
                .build();
        clubRepository.save(club);
    }

    @Override
    public ClubResponse updateClub(Integer id, ClubRequest clubRequest) {
        Optional<Club> optionalClub = clubRepository.findById(id);
        if (optionalClub.isPresent()) {
            Club existingClub =optionalClub.get();
            existingClub.setName(clubRequest.getName());
            existingClub.setDescription(clubRequest.getDescription());
            existingClub.setLogo(clubRequest.getLogo());
            existingClub.setLocation(clubRequest.getLocation());
            existingClub.setContact(clubRequest.getContact());
            existingClub.setStatus(clubRequest.isStatus());
            clubRepository.save(existingClub);
            return ClubResponse.makeClub(existingClub);
        } else {
            throw new NotFoundException("Club with ID : " + id);
        }
    }

    @Override
    public ClubResponse getClubById(Integer clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();
            return ClubResponse.makeClub(club);
        } else {
            throw new NotFoundException("club with ID : " + clubId);
        }
    }
    }
