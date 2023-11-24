package com.example.dto.responseDto;


import com.example.entity.ComDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDocumentResponse {
    private Integer id;
    private CommentResponse comment;
    private Instant createdAt;
    private Instant updatedAt;

    public static CommentDocumentResponse makeCommentDocumentResponse(ComDoc comDoc) {

        return CommentDocumentResponse.builder()
                .id(comDoc.getId())
                .createdAt(comDoc.getCreatedAt())
                .updatedAt(comDoc.getUpdatedAt())
               // .comment(UserResponse.makeUser(comDoc.getComment()))
                .build();
    }
}
