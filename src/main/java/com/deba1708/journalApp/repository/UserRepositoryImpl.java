package com.deba1708.journalApp.repository;

import com.deba1708.journalApp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(
                Criteria.where("email").exists(true),
                Criteria.where("email").ne(null).ne(""),
                Criteria.where("sentimentAnalysis").is(true)
        ));
        return mongoTemplate.find(query, User.class);
    }
}
