package com.library.processservice;

import com.library.processservice.BookDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResourceClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String RESOURCE_URL = "http://localhost:18081/api/books/";

    public BookDto getBook(String bookId) {
        return restTemplate.getForObject(RESOURCE_URL + bookId, BookDto.class);
    }

    public void setAvailability(String bookId, boolean available) {
        restTemplate.put(
                RESOURCE_URL + bookId + "/availability?available=" + available,
                null
        );
    }
}
