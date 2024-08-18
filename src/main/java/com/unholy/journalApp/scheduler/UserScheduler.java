package com.unholy.journalApp.scheduler;


import com.unholy.journalApp.repository.UserEntryRepoImpl;
import com.unholy.journalApp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserEntryRepoImpl userEntryRepo;
    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;


    //    @Scheduled(cron = "*/20 * * * * *")
    public void fetchUsersAndSendSaMail() {
        SentimentData sentimentData = SentimentData.builder().email("anubhavj2002@gmail.com").sentiment("happy").build();
//         if kafka doesnt work then we need a fallback so adding try catch


        try {
            kafkaTemplate.send("weeky-sentiments", "123", sentimentData); //adding key makes it go into the same partition
        } catch (Exception e) {
            log.error("kafka fallback kafka not running sending mail directly", e);
            emailService.sendEmail(sentimentData.getEmail(), "sentiment analysis", sentimentData.getSentiment());
        }

    }
}