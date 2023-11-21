package com.example.service;

import java.util.List;

import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.RegisterRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserDto;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.dto.searchRequest.SearchMember;



public interface UserService {
	List<UserDto> getAllMember();
    UserDto getMemberById(Long id);
    void updateMember(Long id, UpdateMemberRequest request);
    void updateUser(Long id, UpdateUserRequest request);

    UserDto getConnectedUser();
    void deleteMember(Long id);
    void enableMember(Long id);
    List<UserDto> getMemberByFilter(SearchMember serachUserDTO);
    List<UserDto> getAllUserByFilter(SearchAdmin searchAdmin);
    void changePassword(PasswordChangeRequest passwordChangeRequest,Long id);

}
