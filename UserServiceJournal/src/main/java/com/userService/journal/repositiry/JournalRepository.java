package com.userService.journal.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.journal.entity.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {
	
}

