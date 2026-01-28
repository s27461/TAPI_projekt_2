package com.library.reportservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookEventRepository extends MongoRepository<BookEvent, String> {
    List<BookEvent> findByCreatedAtBetween(
            LocalDateTime from,
            LocalDateTime to
    );
}
