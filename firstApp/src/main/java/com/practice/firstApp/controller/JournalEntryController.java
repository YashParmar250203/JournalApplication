package com.practice.firstApp.controller;

import com.practice.firstApp.entity.JournalEntity;
import com.practice.firstApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAll")
    public List<JournalEntity> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntity postEntry(@RequestBody JournalEntity entryObj){
        entryObj.setDate(LocalDateTime.now() );
        journalEntryService.saveEntry(entryObj);
        return entryObj;
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getEntrybyId(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateEntrybyId(@PathVariable ObjectId myId, @RequestBody JournalEntity entryObj){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if (old != null){
            old.setTitle(entryObj.getTitle() != null && !entryObj.getTitle().equals("") ? entryObj.getTitle() : old.getTitle());
            old.setContent(entryObj.getContent() != null && !entryObj.getContent().equals("") ? entryObj.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

    @DeleteMapping("/id/{myId}")
    public String deleteEntrybyId(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return "Success";
    }
}
