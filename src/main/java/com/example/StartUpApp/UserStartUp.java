package com.example.StartUpApp;

import com.example.dto.requestDto.RegisterRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.AuthenticationService;
import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;
import com.example.util.enumData.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
@Component
@RequiredArgsConstructor
public class UserStartUp implements CommandLineRunner {
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
                new SimpleDateFormat("yyyy/M/d").parse("1999/6/9"),
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
                new SimpleDateFormat("yyyy/M/d").parse("1999/6/9"),
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
                new SimpleDateFormat("yyyy/M/d").parse("1999/6/9"),
                "21333444",
                "khnis",
                "civil engineering",
                "Gloulou",
                Post.GENERAL_SECRETARY,
                Office.OFFICE_SOUSSE,
                "https://www.overleaf.com/login?",
                "aez",
                null,
                null,
                null,
                Role.MEMBER,
                null,
                Boolean.TRUE,
                Boolean.FALSE



        ));
        userRepository.save(new User(
                null,
                "admin",
                "admin",
                "admin",
                "admin@gmail.com",
                passwordEncoder.encode("123"),
                Gender.male,
                new SimpleDateFormat("yyyy/M/d").parse("1999/6/9"),
                "21333444",
                "admin",
                "admin",
                "admin",
                Post.NATIONAL_PRESIDENT,
                Office.OFFICE_SOUSSE,
                "https://www.overleaf.com/login?",
                "aze",
                null,
                null,
                null,
                Role.ADMIN,
                null,
                Boolean.TRUE,
                Boolean.FALSE



        ));
    }

}