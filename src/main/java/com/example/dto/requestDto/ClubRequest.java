package com.example.dto.requestDto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private boolean status;
}
