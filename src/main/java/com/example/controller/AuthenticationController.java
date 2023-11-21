package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import com.example.util.Constants;

import jakarta.validation.Valid;


@RestController
@RequestMapping(Constants.APP_ROOT+"/auth")
@RequiredArgsConstructor

public class AuthenticationController {
	 private final AuthenticationService authenticationService;


	    @PostMapping("/register")
	    public ResponseEntity<AuthenticationResponse> register(
	            @RequestBody @Valid RegisterRequest request
	    ){

	        return ResponseEntity.ok(authenticationService.register(request));
	    }

	    @PostMapping("/authenticate")
	    public ResponseEntity<AuthenticationResponse> register(
	            @RequestBody AuthenticationRequest request
	    ){
	        return ResponseEntity.ok(authenticationService.authenticate(request));
	    }

}
