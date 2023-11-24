package com.example.controller.memberController;

import com.example.dto.requestDto.UserClubRequest;
import com.example.dto.responseDto.UserClubResponse;
import com.example.service.UserClubService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER + "/userClub")
@Api(tags = "(Member) User's Club management ")
@CrossOrigin("*")
public class UserClubMemberController {

    private final UserClubService userClubService;

    @PostMapping
    public ResponseEntity<String> joinClub(@RequestBody @Valid UserClubRequest request) {
        userClubService.joinClub(request);
        return ResponseEntity.ok("join with success !!!");
    }

    @GetMapping(value = "/users/{clubId}")
    public ResponseEntity<List<UserClubResponse>> getListOfUserByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(userClubService.getListOfUserByClub(clubId));
    }


}