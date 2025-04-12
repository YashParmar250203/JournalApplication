package com.practice.firstApp.controller;

import com.practice.firstApp.entity.JournalEntity;
import com.practice.firstApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntity>> getAll(){
        List<JournalEntity> listEntity = journalEntryService.getAll();
        if(listEntity != null && !listEntity.isEmpty()){
            return new ResponseEntity<>(listEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> postEntry(@RequestBody JournalEntity entryObj){
        try {
            entryObj.setDate(LocalDateTime.now() );
            journalEntryService.saveEntry(entryObj);
            return new ResponseEntity<>(entryObj, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getEntrybyId(@PathVariable ObjectId myId){
        Optional<JournalEntity> myEntry = journalEntryService.findById(myId);
        if(myEntry.isPresent()){
            return new ResponseEntity<>(myEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> updateEntrybyId(@PathVariable ObjectId myId, @RequestBody JournalEntity entryObj){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if (old != null){
            old.setTitle(entryObj.getTitle() != null && !entryObj.getTitle().equals("") ? entryObj.getTitle() : old.getTitle());
            old.setContent(entryObj.getContent() != null && !entryObj.getContent().equals("") ? entryObj.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> deleteEntrybyId(@PathVariable ObjectId myId){
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
