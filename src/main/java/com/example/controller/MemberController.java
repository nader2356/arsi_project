package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import com.example.util.Constants;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT+"/member")

public class MemberController {
	
	public final UserService userService;

    @GetMapping(value = "/allMember")
    public ResponseEntity<List<UserDto>> getAllMember(){
        return ResponseEntity.ok(userService.getAllMember());
    }

    @GetMapping(value = "/getMember/{id}")
    public ResponseEntity<UserDto> getMemberById(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(userService.getMemberById(id));
    }
    @GetMapping(value = "userConnected")
    public ResponseEntity<UserDto> getUserConnected(){
        return ResponseEntity.ok(userService.getConnectedUser());
    }
}