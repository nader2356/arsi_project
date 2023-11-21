package com.example.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    @NotBlank(message = "old Password is required")
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&.])[A-Za-z\\d@$!%*#?&.]{8,}$",
            message = "The password must contain at least 8 characters, including an uppercase letter, a lowercase letter, a number, and a special symbol.")
    private String newPassword;

}