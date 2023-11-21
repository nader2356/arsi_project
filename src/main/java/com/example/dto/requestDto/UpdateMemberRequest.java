package com.example.dto.requestDto;

import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
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
public class UpdateMemberRequest {

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
    private String job;
    private String universityOrCompany;
    @Enumerated(EnumType.STRING)
    private Office office;
    @URL(message = "this UrlImage is not valid")
    private String image;

}