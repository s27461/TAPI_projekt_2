package com.library.resourceservice;

import org.checkerframework.common.value.qual.IntRangeFromGTENegativeOne;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public class Book {

    @Id
    private String id;

    private String title;
    @Indexed(background = true)
    private boolean available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
