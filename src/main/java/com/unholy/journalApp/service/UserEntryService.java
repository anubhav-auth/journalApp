package com.unholy.journalApp.service;

import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserEntryRepo userEntryRepo;

    public void saveNewUser(User jr) {
        jr.setPassword(passwordEncoder.encode(jr.getPassword()));
        jr.setRoles(List.of("USER"));
        userEntryRepo.save(jr);
    }

    public void saveUser(User jr) {
        userEntryRepo.save(jr);
    }

    public List<User> getAll() {
        return userEntryRepo.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userEntryRepo.findById(id);
    }

    public void deleteByID(ObjectId id) {
        userEntryRepo.deleteById(id);
    }

    public User findByUserName(String username) {
        return userEntryRepo.findByUsername(username);
    }


    public void saveAdmin(User jr) {
        jr.setPassword(passwordEncoder.encode(jr.getPassword()));
        jr.setRoles(List.of("USER", "ADMIN"));
        userEntryRepo.save(jr);
    }
}
