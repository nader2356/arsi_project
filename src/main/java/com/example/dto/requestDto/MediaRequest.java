package com.example.dto.requestDto;

import com.example.util.enumData.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaRequest {


    private String title;
    private String description;
    private MediaType type;
    private String image;
    private String urlPost;


}