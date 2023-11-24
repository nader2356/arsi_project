package com.example.service;

import java.util.List;

import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserResponse;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.dto.searchRequest.SearchMember;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;




public interface UserService {
	List<UserResponse> getAllMember();
    UserResponse getMemberById(Long id);
    void updateMember(Long id, UpdateMemberRequest request);
    void updateUser(Long id, UpdateUserRequest request);

    UserResponse getConnectedUser();
    void deleteMember(Long id);
    void enableMember(Long id);
    
    Page<UserResponse> getAllUserByFilter(SearchAdmin searchAdmin, Pageable pageable);
    void changePassword(PasswordChangeRequest passwordChangeRequest,Long id);
    
    void forgotPassword(String username) ;


    void resetPasswordWithOTP(String username, String otp, String newPassword) ;

}
