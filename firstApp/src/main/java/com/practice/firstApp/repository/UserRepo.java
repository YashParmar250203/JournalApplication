package com.practice.firstApp.repository;

import com.practice.firstApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
}
