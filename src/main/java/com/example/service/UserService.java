package com.example.service;

import java.util.List;

import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserResponse;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.dto.searchRequest.SearchMember;
import org.springframework.web.multipart.MultipartFile;




public interface UserService {
	List<UserResponse> getAllMember();
    UserResponse getMemberById(Long id);
    void updateMember(Long id, UpdateMemberRequest request);
    void updateUser(Long id, UpdateUserRequest request);

    UserResponse getConnectedUser();
    void deleteMember(Long id);
    void enableMember(Long id);
    List<UserResponse> getMemberByFilter(SearchMember serachUserDTO);
    List<UserResponse> getAllUserByFilter(SearchAdmin searchAdmin);
    void changePassword(PasswordChangeRequest passwordChangeRequest,Long id);
    
    void forgotPassword(String username) ;


    void resetPasswordWithOTP(String username, String otp, String newPassword) ;

    void uploadImage(MultipartFile file,Long id);

}
