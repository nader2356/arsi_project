package com.example.service.serviceImpl;

import com.example.dto.requestDto.UserClubRequest;
import com.example.dto.responseDto.UserClubResponse;
import com.example.entity.*;
import com.example.exception.NotFoundException;
import com.example.repository.*;
import com.example.service.UserClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClubServiceImpl implements UserClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;

    @Override
    public void addUserClub(UserClubRequest request) {
        User user = userRepository.findById(Long.valueOf(request.getUserId()))
                .orElseThrow(() -> new NotFoundException("User not found"));
        Club club = clubRepository.findById(request.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found"));

        UserClub userClub = new UserClub();
        userClub.setUser(user);
        userClub.setClub(club);
        userClubRepository.save(userClub);
    }
    @Override
    public List<UserClubResponse> getAllClub(Long id) {
        List<UserClub> userClubs = userClubRepository.findByUserId(id);
        List<UserClubResponse> userClubResponses = new ArrayList<>();
        for (UserClub userClub : userClubs) {
            UserClubResponse userClubResponse = new UserClubResponse();
            userClubResponse.setId(userClub.getId());
            userClubResponses.add(userClubResponse);
        }
        return userClubResponses;
    }
    @Override
    public boolean deleteUserClub(Long id) {
        UserClub userClub = userClubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserClub not found"));
        userClubRepository.delete(userClub);
        return true;
    }
    @Override
    public void joinClub(UserClubRequest request) {
        User user = userRepository.findById(Long.valueOf(request.getUserId()))
                .orElseThrow(() -> new NotFoundException("User not found"));
        Club club = clubRepository.findById(request.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found"));
        boolean isMember = userClubRepository.existsByUserAndClub(user, club);
        if (!isMember) {
            UserClub userClub = new UserClub();
            userClub.setUser(user);
            userClub.setClub(club);
            userClubRepository.save(userClub);
        } else {
            throw new IllegalStateException("User is already a member of the club.");
        }
    }
    @Override
    public List<UserClubResponse> getListOfUserByClub(Long clubId)
    {
        List<UserClub> userClubs = userClubRepository.findAllByClubId(clubId);
        List<UserClubResponse> userClubResponses = new ArrayList<>();
        for (UserClub userClub : userClubs) {
            UserClubResponse userClubResponse = UserClubResponse.makeUserClubResponse(userClub);
            userClubResponses.add(userClubResponse);
        }
        return userClubResponses;
    }
    @Override
    public List<UserClubResponse> getAllClubByUser(Long id)
    {
        List<UserClubResponse> userClubResponses = new ArrayList<>();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<UserClub> userClubs = userClubRepository.findByUser(user);
        for (UserClub userClub : userClubs) {
            UserClubResponse userClubResponse = new UserClubResponse();
            userClubResponse.setId(userClub.getId());
            userClubResponses.add(userClubResponse);
        }
        return userClubResponses;
    }


    @Override
    public boolean updateUserClub(Long id, UserClubRequest request) {
        UserClub userClub = userClubRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserClub not found"));
        User user = userClub.getUser();
        Club club = userClub.getClub();
        if(request.getUserId() != null) {
            User newUser = userRepository.findById(Long.valueOf(request.getUserId()))
                    .orElseThrow(() -> new NotFoundException("User not found"));
            userClub.setUser(newUser);
        }
        if (request.getClubId() != null) {
            Club newClub = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found"));
            userClub.setClub(newClub);
        }
        userClubRepository.save(userClub);
        return true;
    }

}