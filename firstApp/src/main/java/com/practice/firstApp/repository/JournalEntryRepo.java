package com.practice.firstApp.repository;

import com.practice.firstApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId> {

}
