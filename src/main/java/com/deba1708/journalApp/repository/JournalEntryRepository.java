package com.deba1708.journalApp.repository;

import com.deba1708.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

// repository is talk with DataBase with the help of MongoRepository
