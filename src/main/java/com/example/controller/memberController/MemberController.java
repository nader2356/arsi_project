package com.example.controller.memberController;

import java.util.Collections;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.dto.responseDto.UserResponse;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import com.example.util.Constants;
import io.swagger.annotations.Api;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER)
@Api(tags = "(Member) User Management ")
@CrossOrigin("*")
public class MemberController {
    public final UserService userService;
    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllMember() {
        return ResponseEntity.ok(userService.getAllMember());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<UserResponse> getMemberById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getMemberById(id));
    }
    @GetMapping(value = "me")
    public ResponseEntity<UserResponse> getMe() {
        return ResponseEntity.ok(userService.getConnectedUser());
    }
    @PostMapping(value = "/filter")
    public ResponseEntity<Page<UserResponse>> getAllMember(@RequestBody SearchAdmin searchAdmin, Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUserByFilter(searchAdmin,pageable));
    }

    @PutMapping
    public ResponseEntity<Object> updateMe(@RequestBody UpdateMemberRequest request) {
        UserResponse user = userService.getConnectedUser();
        userService.updateMember(user.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success!!"));
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> changeMyPassword(@RequestBody PasswordChangeRequest request) {
        UserResponse user = userService.getConnectedUser();
        userService.changePassword(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "Password changed successfully !!"));
    }


}