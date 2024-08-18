package com.unholy.journalApp.repository;

import com.unholy.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();

        query.addCriteria(
                Criteria.where("username").is("Anubhav")
        ).addCriteria(
                Criteria.where("sentimentAnalysis").is(true)
        ).addCriteria(
                Criteria.where("email").exists(true)
        ).addCriteria(Criteria.where("email").ne(null).ne(""));


        List<User> users = mongoTemplate.find(query, User.class);

        return users;

    }
}

