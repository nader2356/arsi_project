package com.example.dto.responseDto;

import com.example.entity.Contact;
import com.example.util.enumData.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    public static List<ContactResponse> makeContacts(List<Contact> contacts) {
        List<ContactResponse> contactResponses = new ArrayList<>();
        for (Contact contact : contacts) {
            ContactResponse contactResponse = makeContact(contact);
            contactResponses.add(contactResponse);
        }
        return contactResponses;
    }

}