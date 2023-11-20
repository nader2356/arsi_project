package com.example.service;

import java.util.List;

import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;

public interface UserService {
	   List<UserDto> getAllMember();
	    UserDto getMemberById(Long id);
	    void updateMember(Long id, RegisterRequest request);
	    UserDto getConnectedUser();
	    void deleteMember(Long id);
	    void enableMember(Long id);

}
