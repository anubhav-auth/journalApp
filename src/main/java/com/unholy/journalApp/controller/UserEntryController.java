package com.unholy.journalApp.controller;


import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.entity.WeatherApi;
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
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepo userEntryRepo;
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<User> getAllUsers() {
        return userEntryService.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();


        User userInDB = userEntryService.findByUserName(userName);

        if (userInDB != null) {
            userInDB.setUsername((user.getUsername()));
            userInDB.setPassword((user.getPassword()));
            userEntryService.saveNewUser(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepo.deleteUserByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherApi serviceWeather = weatherService.getWeather("kolkata");
        String greet = "";
        if (serviceWeather != null){
            greet = " ,weather feels like " + serviceWeather.current.temp_c;
        }
        return new ResponseEntity<>("hi " + authentication.getName() + greet, HttpStatus.OK);
    }


}