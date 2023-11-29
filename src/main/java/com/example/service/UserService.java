package com.example.service;

import java.util.List;
import java.util.UUID;

import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserResponse;
import com.example.dto.searchRequest.SearchAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface UserService {
	  List<UserResponse> getAllMember();

	    
	    UserResponse getMemberById(UUID id);

	  
	    void updateMember(UUID id, UpdateMemberRequest request);

	    
	    void updateUser(UUID id, UpdateUserRequest request);

	    UserResponse getConnectedUser();

	    
	    void deleteMember(UUID id);

	   ;
	    void enableMember(UUID id);





	    Page<UserResponse> getAllUserByFilter(SearchAdmin searchAdmin, Pageable pageable);

	    
	    void changePassword(PasswordChangeRequest passwordChangeRequest, UUID id);

	     void forgotPassword(String username) ;

	     void resetPasswordWithOTP(String username, String otp, String newPassword) ;

}
