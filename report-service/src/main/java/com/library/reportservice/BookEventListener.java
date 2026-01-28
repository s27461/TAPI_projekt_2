package com.library.reportservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookEventListener {

    private final BookEventRepository repository;
    private final PdfGeneratorService pdfGeneratorService;

    public BookEventListener(BookEventRepository repository, PdfGeneratorService pdfGeneratorService) {
        this.repository = repository;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @RabbitListener(queues = "book.events")
    public void receiveMessage(String message) {
        BookEvent event = new BookEvent(message, LocalDateTime.now());
        repository.save(event);
        System.out.println("Zapisano event: " + message);
        try {
        pdfGeneratorService.generatePdf(event);
        System.out.println("PDF wygenerowany: " + event.getId() ); }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
