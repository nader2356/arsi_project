package com.example.dto.searchRequest;


import com.example.util.enumData.Gender;
import com.example.util.enumData.Office;
import com.example.util.enumData.Post;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;



@Data
public class SearchMember {
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String region;
    private String job;
    private String universityOrCompany;
    @Enumerated(EnumType.STRING)
    private Post post;
    @Enumerated(EnumType.STRING)
    private Office office;

}