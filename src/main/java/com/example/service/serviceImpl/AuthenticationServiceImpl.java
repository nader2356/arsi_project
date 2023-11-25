package com.example.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.requestDto.AuthenticationRequest;

import com.example.dto.requestDto.RegisterRequest;
import com.example.dto.responseDto.AuthenticationResponse;
import com.example.exception.ConflictException;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.AuthenticationService;
import com.example.util.EmailUtil;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailUtil emailUtil;
    
    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ConflictException(String.format("this email is already exist ( [%s] ) ",request.getEmail()));
        }
         if ( userRepository.findByUserName(request.getUserName()).isPresent()){
            throw new ConflictException(String.format("this email is already exist ( [%s] ) ",request.getUserName()));
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())
                .region(request.getRegion())
                .job(request.getJob())
                .universityOrCompany(request.getUniversityOrCompany())
                .password(passwordEncoder.encode(request.getPassword()))
                .deleted(false)
                .status(false)
                .isPaid(false)
                .role( Role.MEMBER)
                .post(Post.MEMBER)
                .office(request.getOffice())
                .build();
        userRepository.save(user);
        String subject = "Confirmation of Your Account Creation";
        String content = "We are delighted to inform you that your account has been successfully created using this email address.";
        String fromEmail ="mbarekk.skandar@gmail.com" ;
        emailUtil.sendEmail(user.getEmail(),fromEmail,subject,content);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

	

}
