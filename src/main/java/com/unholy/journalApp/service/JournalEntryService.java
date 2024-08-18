package com.unholy.journalApp.service;

import com.unholy.journalApp.entity.JournalEntry;
import com.unholy.journalApp.entity.User;
import com.unholy.journalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo jrRepo;
    @Autowired
    private UserEntryService userEntryService;


    @Transactional
    public void saveEntry(JournalEntry jr, String username) {
        User user = userEntryService.findByUserName(username);
        JournalEntry saved = jrRepo.save(jr);
        user.getJournalEntries().add(saved);
        userEntryService.saveUser(user);
        log.trace("hahahhahahahaha");
        log.debug("hahahhahahahaha");
        log.info("hahahhahahahaha");
        log.warn("hahahhahahahaha");
        log.error("hahahhahahahaha");
    }

    public void saveEntry(JournalEntry jr) {
        jrRepo.save(jr);
    }

    public List<JournalEntry> getAll() {
        return jrRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return jrRepo.findById(id);
    }

    @Transactional
    public void deleteByID(String username, ObjectId id) {
        User user = userEntryService.findByUserName(username);
        Boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userEntryService.saveUser(user);
            jrRepo.deleteById(id);
        }

    }
}
