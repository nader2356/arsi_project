package com.example.service;

import com.example.dto.requestDto.UserClubRequest;
import com.example.dto.responseDto.UserClubResponse;

import java.util.List;
import java.util.UUID;

public interface UserClubService {
	  void addUserClub(UserClubRequest request);

	    
	    List<UserClubResponse> getAllClub(UUID id);

	    
	    List<UserClubResponse> getAllClubByUser(UUID id);

	    boolean updateUserClub(Long id, UserClubRequest request);

	    boolean deleteUserClub(Long id);
	    void joinClub(UserClubRequest request);
	    List<UserClubResponse> getListOfUserByClub(Long clubId);
}