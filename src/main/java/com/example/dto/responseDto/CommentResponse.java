package com.example.dto.responseDto;

import com.example.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Integer id;
    private String text;
    private Instant createdAt;
    private Instant updatedAt;

    public static CommentResponse makeComment(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
