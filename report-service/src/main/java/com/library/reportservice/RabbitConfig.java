package com.library.reportservice;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String BOOK_EVENTS_QUEUE = "book.events";

    @Bean
    public Queue bookEventsQueue() {
        return new Queue(BOOK_EVENTS_QUEUE, true);
    }
}
