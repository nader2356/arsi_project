package com.example.service;

import com.example.dto.requestDto.AuthenticationRequest;

import com.example.dto.requestDto.RegisterRequest;
import com.example.dto.responseDto.AuthenticationResponse;

public interface AuthenticationService {
	 AuthenticationResponse register(RegisterRequest request);
	    AuthenticationResponse authenticate(AuthenticationRequest request);
}
