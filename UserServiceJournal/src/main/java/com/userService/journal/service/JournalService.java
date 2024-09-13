package com.userService.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userService.journal.entity.Journal;
import com.userService.journal.repositiry.JournalRepository;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public void recordEvent(String eventMessage) {
        Journal journal = new Journal(eventMessage);
        journalRepository.save(journal);
    }

    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    public Journal getJournalById(Long id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found"));
    }
}

