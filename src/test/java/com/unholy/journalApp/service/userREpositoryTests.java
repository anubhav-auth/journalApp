package com.unholy.journalApp.service;


import com.unholy.journalApp.repository.UserEntryRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userREpositoryTests {

    @Autowired
    private UserEntryRepoImpl userEntryRepo;

    @Test
    public void test1(){
        userEntryRepo.getUserForSA();
    }

}
