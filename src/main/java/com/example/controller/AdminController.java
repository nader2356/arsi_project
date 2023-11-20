package com.example.controller;

import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import com.example.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT+"/admin")

public class AdminController {
	public final UserService userService;

    @PutMapping(value = "enableAccount/{id}")
    public ResponseEntity<String> enableMember(@PathVariable(name = "id") Long id){
        userService.enableMember(id);
        return ResponseEntity.ok("This Account enabled with success !!!!!");
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable(name = "id") Long id){
        userService.deleteMember(id);
        return ResponseEntity.ok("this Account is deleted");
    }
}
