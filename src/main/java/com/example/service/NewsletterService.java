package com.example.service;


import com.example.dto.requestDto.NewsletterRequest;
import com.example.dto.responseDto.NewsletterResponse;

import java.util.List;

public interface NewsletterService {
    void subscribe(String email);

    List<NewsletterResponse> getAllSubscribers();

    List<String> getAllSubscribersById(Integer id);

    void createNewsletter(NewsletterRequest newsletterRequest);

    void deleteNewsletter(Integer id);
}
