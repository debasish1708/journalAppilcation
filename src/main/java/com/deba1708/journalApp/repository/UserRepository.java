package com.deba1708.journalApp.repository;


import com.deba1708.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String username);
}

// repository is talk with DataBase with the help of MongoRepository
