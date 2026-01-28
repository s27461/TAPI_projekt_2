package com.library.reportservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("events")
public class BookEvent {
    @Id
    private String id;
    private String message;

    private LocalDateTime createdAt;
    public BookEvent() {
    }

    public BookEvent(String message, LocalDateTime createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}
