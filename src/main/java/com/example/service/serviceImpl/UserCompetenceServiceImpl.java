package com.example.service.serviceImpl;

import com.example.dto.requestDto.UserCompetenceRequest;
import com.example.dto.responseDto.UserCompetenceResponse;
import com.example.dto.responseDto.UserResponse;
import com.example.entity.Competence;
import com.example.entity.User;
import com.example.entity.UserCompetence;
import com.example.exception.NotFoundException;
import com.example.repository.CompetenceRepository;
import com.example.repository.UserCompetenceRepository;
import com.example.repository.UserRepository;
import com.example.service.UserCompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserCompetenceServiceImpl implements UserCompetenceService {
    private final UserRepository userRepository;
    private final CompetenceRepository competenceRepository;
    private final UserCompetenceRepository userCompetenceRepository;
    @Override
    public void addUserCompetence(UserCompetenceRequest userCompetenceRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> user = userRepository.findByUserName(currentUserName);
        if (user.isEmpty()) {
            throw new NotFoundException("mafamech User *************");
        }
        Competence competence = competenceRepository.findById(userCompetenceRequest.getCompetenceId()).orElseThrow(
                () -> new NotFoundException(String.format("this Competence with id [%s] is not exist", userCompetenceRequest.getCompetenceId())));
        UserCompetence userCompetence = UserCompetence.builder()
                .competence(competence)
                .user(user.get())
                .level(userCompetenceRequest.getLevel())
                .build();
        userCompetenceRepository.save(userCompetence);
    }
    @Override
    public void updateUserCompetence(Long id, UserCompetenceRequest userCompetenceRequest) {

        UserCompetence userCompetence = userCompetenceRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this UserCompetence with id [%s] is not exist", id)));
       
        if (userCompetenceRequest.getCompetenceId() != null) {
            Competence competence = competenceRepository.findById(userCompetenceRequest.getCompetenceId()).orElseThrow(
                    () -> new NotFoundException(String.format("this Competence with id [%s] is not exist", userCompetenceRequest.getCompetenceId())));

            userCompetence.setCompetence(competence);
        }
        if (userCompetenceRequest.getLevel() != null) {

            userCompetence.setLevel(userCompetenceRequest.getLevel());
        }


        userCompetenceRepository.save(userCompetence);
    }
    @Override
    public List<UserCompetenceResponse> getAllCompetenceByUser(Long id) {
        List<UserCompetence> userCompetences = userCompetenceRepository.findAllByUserId(id);
        List<UserCompetenceResponse> userCompetenceResponses = new ArrayList<>();
        for (UserCompetence userCompetence : userCompetences) {
            UserCompetenceResponse userCompetenceResponse = UserCompetenceResponse.makeUserCompetence(userCompetence);
            userCompetenceResponses.add(userCompetenceResponse);
        }
        return userCompetenceResponses;
    }
    @Override
    public List<UserResponse> getAllUserByCompetence(Long id) {
        List<UserCompetence> userCompetences = userCompetenceRepository.findAllByCompetenceId(id);
        List<UserResponse> userDtos = new ArrayList<>();
        for (UserCompetence userCompetence : userCompetences) {
            UserResponse userDto = UserResponse.makeUser(userCompetence.getUser());
            userDtos.add(userDto);
        }
        return userDtos;
    }
    @Override
    public void deleteUserCompetence(Long id) {
        if (!userCompetenceRepository.existsById(id)) {
            throw new NotFoundException(String.format("this userCompetence with id [%s] is not exist", id));
        }
        userCompetenceRepository.deleteById(id);
    }
}