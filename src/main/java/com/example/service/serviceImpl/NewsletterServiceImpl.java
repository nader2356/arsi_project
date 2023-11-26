package com.example.service.serviceImpl;

import com.example.dto.requestDto.NewsletterRequest;
import com.example.dto.responseDto.NewsletterResponse;
import com.example.entity.Newsletter;
import com.example.exception.DuplicateSubscriptionException;
import com.example.exception.NotFoundException;
import com.example.repository.NewsletterRepository;
import com.example.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private List<String> subscribers= new ArrayList<>();

    @Override
    public void subscribe(String email) {
       if(!subscribers.contains(email)) {
           subscribers.add(email);
       }else{
           throw new DuplicateSubscriptionException("the email is already subscribed to the newsletter");
       }
       }

    @Override
    public List<NewsletterResponse> getAllSubscribers() {
            List<Newsletter> newsletters = newsletterRepository.findAll();
            List<NewsletterResponse> newsletterResponses = new ArrayList<>();
            for (Newsletter newsletter : newsletters) {
                NewsletterResponse newsletterDto = NewsletterResponse.makeNewsletter(newsletter);
                newsletterResponses.add(newsletterDto);
            }
            return newsletterResponses;
        }
    @Override
    public List<String> getAllSubscribersById(Integer id) {
        Optional<Newsletter> newsletterOptional = newsletterRepository.findById(id);
        if (newsletterOptional.isPresent()) {
            Newsletter newsletter = newsletterOptional.get();
            return newsletter.getSubscribers();
        } else {
            throw new NotFoundException("Newsletter with ID " + id );
        }
    }
    @Override
    public void createNewsletter(NewsletterRequest newsletterRequest) {
        Newsletter newsletter = Newsletter.builder()
                .email(newsletterRequest.getEmail())
                .build();
        newsletterRepository.save(newsletter);
    }
    @Override
    public void deleteNewsletter(Integer id) {
        newsletterRepository.deleteById(id);
    }
}
