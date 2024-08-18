package com.unholy.journalApp.controller;


import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.service.UserDetailServiceImpl;
import com.unholy.journalApp.service.UserEntryService;
import com.unholy.journalApp.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "running OK";
    }


    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userEntryService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
            String token = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured while authToken creation", e);
            return new ResponseEntity<>("Incorrect UserName or Password", HttpStatus.BAD_REQUEST);
        }

    }
}
