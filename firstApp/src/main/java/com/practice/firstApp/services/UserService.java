package com.practice.firstApp.services;

import com.practice.firstApp.entity.UserEntity;
import com.practice.firstApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void saveUserEntry(UserEntity userNew){
        userRepo.save(userNew);
    }

    public List<UserEntity> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<UserEntity> findUserById(ObjectId userId){
        return userRepo.findById(userId);
    }

    public void deleteUserById(ObjectId userId){
        userRepo.deleteById(userId);
    }
}
