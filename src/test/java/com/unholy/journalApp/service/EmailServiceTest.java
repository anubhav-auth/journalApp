package com.unholy.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;


    @Test
    public void mailTest() {
        emailService.sendEmail("anubhavj2002@gmail.com", "testing", "mail sent from spring boot");
    }
}
