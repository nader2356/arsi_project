package com.example.service;

import com.example.dto.requestDto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.requestDto.RegisterRequest;

public interface AuthenticationService {
	 AuthenticationResponse register(RegisterRequest request);
	    AuthenticationResponse authenticate(AuthenticationRequest request);
}
