package com.example.dto.requestDto;

import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {


    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Username is required")
    private String userName;
    @Email(message ="your email is not valid" )
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Pattern(regexp = "^[0-9]{8}$",message = "phone number not valid")
    private String phoneNumber;
    @NotBlank(message = "your region is required")
    private String region;
    @NotBlank(message = "job is required")
    private String job;
    @NotBlank(message = "universityOrCompany is required")
    private String universityOrCompany;
    @Enumerated(EnumType.STRING)
    private Office office;
    @URL(message = "this UrlImage is not Valid")
    private String image;
    @Enumerated(EnumType.STRING)
    private Post post;
    @Enumerated(EnumType.STRING)
    private Role role;

}