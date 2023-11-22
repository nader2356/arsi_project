package com.example.dto.responseDto;

import com.example.entity.Contact;
import com.example.util.enumData.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {

    private Long id;
    private Platform platform;
    private String url;
    private Instant createdAt;
    private Instant updatedAt;

    public static ContactResponse makeContact(Contact contact){
        return ContactResponse.builder()
                .id(contact.getId())
                .platform(contact.getPlatform())
                .url(contact.getUrl())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt()).build();
    }

}