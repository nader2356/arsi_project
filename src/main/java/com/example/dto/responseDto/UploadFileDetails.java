package com.example.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileDetails {

    private String fileName;
    private String fileDisplayUri;
    private String fileType;
    private Long size;
}
