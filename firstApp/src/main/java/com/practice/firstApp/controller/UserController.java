package com.practice.firstApp.controller;

import com.practice.firstApp.entity.UserEntity;
import com.practice.firstApp.services.UserService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        List<UserEntity> all = userService.getAllUser();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user){
        try{
            userService.saveUserEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping
//    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user){
//        UserEntity userInDB = userService.findUserByUsername()
//    }

//    @DeleteMapping
//    public ResponseEntity deleteUser(ObjectId)
}
