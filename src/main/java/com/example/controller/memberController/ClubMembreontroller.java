package com.example.controller.memberController;

import com.example.dto.responseDto.ClubResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


import com.example.service.ClubService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@Transactional
@RequestMapping(Constants.APP_ROOT_MEMBER + "/club")
@Api(tags = "(Admin) Club Management")
@CrossOrigin("*")
public class ClubMembreontroller {
    private final ClubService clubService;

    public ClubMembreontroller(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("")
    public ResponseEntity<List<ClubResponse>> getAllClub() {
        List<ClubResponse> clubs = clubService.getAllClub();
        return ResponseEntity.ok(clubs);
    }

}
