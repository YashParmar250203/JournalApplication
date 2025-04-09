package com.practice.firstApp.services;

import com.practice.firstApp.entity.JournalEntity;
import com.practice.firstApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntity journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId myId){
        return journalEntryRepo.findById(myId);
    }

    public void deleteById(ObjectId myId){
        journalEntryRepo.deleteById(myId);
    }

}
