package com.unholy.journalApp.service;

import com.unholy.journalApp.repository.UserEntryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserEntryServiceTests {
    @Autowired
    private UserEntryRepo userEntryRepo;

    @Test
    public void testFindByUsername(){
        assertNotNull(userEntryRepo.findByUsername("Anubhav"));
    }
}
