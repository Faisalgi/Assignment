package com.userService.journal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventMessage;
    
    private LocalDateTime timestamp;

    public Journal() {}

    public Journal(String eventMessage) {
        this.eventMessage = eventMessage;
        this.timestamp = LocalDateTime.now();
    }

	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    
}

