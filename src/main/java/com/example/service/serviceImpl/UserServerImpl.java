package com.example.service.serviceImpl;

import java.util.ArrayList;

import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServerImpl implements UserService {

	    private final UserRepository userRepository;

	    @Override
	    public List<UserDto> getAllMember() {

	        List<User> users = userRepository.findAllMember();
	        List<UserDto> members = new ArrayList<>();
	        for (User user:users) {
	            UserDto member = UserDto.makeUser(user);
	            members.add(member);
	        }
	        return members;
	    }

	    @Override
	    public UserDto getMemberById(Long id) {
	        User user = userRepository.findById(id).orElseThrow();
	        System.out.println("*********************&&&&&&&&&&&&&&&&&&&&&&"+user.getAuthorities());
	        return UserDto.makeUser(user);
	    }

	    @Override
	    public void updateMember(Long id, RegisterRequest request) {

	    }

	    @Override
	    public UserDto getConnectedUser() {
	        System.out.println(SecurityContextHolder.getContext().getAuthentication());
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String currentUserName = authentication.getName();
	        Optional<User> user = userRepository.findByUserName(currentUserName);
	        if (user.isPresent()) {
	            return UserDto.makeUser(user.get());
	        }else throw new RuntimeException("mafamech User *************");
	    }

	    @Override
	    public void deleteMember(Long id) {

	        userRepository.deleteById(id);

	    }

	    @Override
	    public void enableMember(Long id) {

	        User user = userRepository.findById(id).orElseThrow();
	        Calendar today = Calendar.getInstance();
	        today.add(Calendar.YEAR,1);
	        user.setExpiresAt(today.toInstant());

	        userRepository.save(user);

	    }
}
