package com.example.dto.requestDto;

import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class RegisterRequest {
	
	@NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Username is required")
    private String userName;
    @Email(message ="your email is not valid" )
    private String email;
    @Pattern(regexp = "^.{8,}$",
            message = "The password must contain at least 8 characters, including an uppercase letter, a lowercase letter, a number, and a special symbol.")
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dateOfBirth;
    @Pattern(regexp = "^[0-9]{8}$",message = "phone number not valid")
    private String phoneNumber;
    @NotBlank(message = "your region is required")
    private String region;
    private String job;
    private String universityOrCompany;
    @Enumerated(EnumType.STRING)
    private Office office;
    

}
