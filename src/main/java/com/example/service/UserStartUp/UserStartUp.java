package com.example.service.UserStartUp;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dto.RegisterRequest;
import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserStartUp {
	private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        authenticationService.register(new RegisterRequest("Skandar",
                "Mbarek",
                "SkandarMB",
                "skandar.MB50@gmail.com",
                "Skandar_mb50**",
                Gender.male,
                "93255601",
                "Bembla",
                "Batal",
                "fi dar",
                Office.OFFICE_SOUSSE));
        authenticationService.register(new RegisterRequest("ali",
                "salem",
                "alisalem",
                "ali.salem@gmail.com",
                "Skandar_mb50**",
                Gender.male,
                "93255601",
                "sousse",
                "ingenieur",
                "fi dar",
                Office.OFFICE_SOUSSE));
        userRepository.save(new User(
                null,
                "mohamed",
                "ali",
                "mohamedAli",
                "mohamedAli@gmail.com",
                passwordEncoder.encode("Skandar_mb50**"),
                Gender.male,
                "21333444",
                "khnis",
                "civil engineering",
                "Gloulou",
                Post.GENERAL_SECRETARY,
                Office.OFFICE_SOUSSE,
                null,
                null,
                null,
                Role.MEMBER,
                Boolean.FALSE


                ));
        userRepository.save(new User(
                null,
                "admin",
                "admin",
                "admin",
                "admin@gmail.com",
                passwordEncoder.encode("Skandar_mb50**"),
                Gender.male,
                "21333444",
                "admin",
                "admin",
                "admin",
                Post.NATIONAL_PRESIDENT,
                Office.OFFICE_SOUSSE,
                null,
                null,
                null,
                Role.ADMIN,
                Boolean.FALSE


        ));
    }

}
