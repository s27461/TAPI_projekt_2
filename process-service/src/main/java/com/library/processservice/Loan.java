package com.library.processservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("loans")
public class Loan {

    @Id
    private String id;

    private BookDto book;
    private String userId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public String getBookId() {
        return book.getId();
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
