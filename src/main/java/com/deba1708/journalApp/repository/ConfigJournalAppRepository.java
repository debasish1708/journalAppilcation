package com.deba1708.journalApp.repository;


import com.deba1708.journalApp.entity.ConfigJournalAppEntity;
import com.deba1708.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}

// repository is talk with DataBase with the help of MongoRepository
