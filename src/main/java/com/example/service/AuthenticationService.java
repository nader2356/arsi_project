package com.example.service;

import com.example.backendarsii.dto.requestDto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.backendarsii.dto.requestDto.RegisterRequest;

public interface AuthenticationService {
	 AuthenticationResponse register(RegisterRequest request);
	    AuthenticationResponse authenticate(AuthenticationRequest request);
}
