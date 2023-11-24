package com.example.dto.requestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {

    private Integer id;
    private String title;
    private String description;
    private String image;
    private String URLDocument;
    private String typedoc;
}