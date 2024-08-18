package com.unholy.journalApp.service;

import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.repository.UserEntryRepo;
import com.unholy.journalApp.scheduler.SentimentData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weeky-sentiments", groupId = "weeky-sentiments-group")
    public void consume(SentimentData sentimentData){
       sendEmail(sentimentData);
       log.info("email sent");
    }

    private void sendEmail(SentimentData sentimentData){
        emailService.sendEmail(sentimentData.getEmail(), "sentiment analysis", sentimentData.getSentiment());
    }
}
