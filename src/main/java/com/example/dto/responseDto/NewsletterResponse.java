package com.example.dto.responseDto;


import com.example.entity.Newsletter;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class NewsletterResponse {

    private Integer id;
    private String email;
    private Instant createdAt;
    private Instant UpdatedAt;

    public static NewsletterResponse makeNewsletter(Newsletter newsletter){
        return NewsletterResponse.builder()
                .id(newsletter.getId())
                .email(newsletter.getEmail())
                .createdAt(newsletter.getCreatedAt())
                .UpdatedAt(newsletter.getUpdatedAt())
                .build();
    }
}
