package com.example.dto.requestDto;



import com.example.util.enumData.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubRequest {

    private Integer id;
    private String name;
    private String logo;
    private String location;
    private String description;
    private String contact;
    private Date date;
    private String member;
    @Enumerated(EnumType.STRING)
    private Post post;
    private boolean status;
}
