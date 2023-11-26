package com.example.dto.requestDto;

import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;





@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {



    private String firstName;
    private String lastName;  
    private String userName;
    @Email(message = "your email is not valid")
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dateOfBirth;
    @Pattern(regexp = "^[0-9]{8}$", message = "phone number not valid")
    private String phoneNumber;
    private String region;
    private String job;
    private String universityOrCompany;
    @Enumerated(EnumType.STRING)
    private Office office;
    private String image;
    private String cv;
    @Enumerated(EnumType.STRING)
    private Post post;
    @Enumerated(EnumType.STRING)
    private Role role;

}