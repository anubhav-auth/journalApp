package com.unholy.journalApp.repository;

import com.unholy.journalApp.entity.ConfigJournalAppEntity;
import com.unholy.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}

