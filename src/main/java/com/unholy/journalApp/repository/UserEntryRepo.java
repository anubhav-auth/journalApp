package com.unholy.journalApp.repository;

import com.unholy.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteUserByUsername(String username);

}

