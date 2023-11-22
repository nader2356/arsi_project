package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.responseDto.AuthenticationResponse;
import com.example.service.AuthenticationService;
import com.example.service.UserService;
import com.example.dto.requestDto.AuthenticationRequest;
import com.example.dto.requestDto.RegisterRequest;


import lombok.RequiredArgsConstructor;
import com.example.util.Constants;

import io.swagger.annotations.Api;

import jakarta.validation.Valid;


@RestController
@RequestMapping(Constants.APP_ROOT+"/auth")
@RequiredArgsConstructor
@Api(tags = "Authentication Management")

@CrossOrigin("*")
public class AuthenticationController {
	 private final AuthenticationService authenticationService;
	 private final UserService userService;


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
        
	    @PostMapping("/forgot-password")
	    public String forgotPassword(@RequestParam String username) {
	        userService.forgotPassword(username);
	        return "OTP sent to your email.";
	    }

	    @PostMapping("/reset-password")
	    public String resetPassword(
	            @RequestParam String username,
	            @RequestParam String otp,
	            @RequestParam String newPassword
	    ) {
	        userService.resetPasswordWithOTP(username, otp, newPassword);
	        return "Password reset successfully.";
	    }
}
