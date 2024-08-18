package com.unholy.journalApp.controller;


import com.unholy.journalApp.cache.AppCache;
import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.entity.WeatherApi;
import com.unholy.journalApp.repository.ConfigJournalAppRepo;
import com.unholy.journalApp.repository.UserEntryRepo;
import com.unholy.journalApp.service.UserEntryService;
import com.unholy.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cache")
public class ConfigJournalAppController {
    @Autowired
    AppCache appCache;

    @GetMapping
    public void refreshCache(){
        appCache.init();
    }
}