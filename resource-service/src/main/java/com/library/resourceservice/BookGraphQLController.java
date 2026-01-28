package com.library.resourceservice;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class BookGraphQLController {

    private final BookRepository repo;
    private final BookPublisher bookPublisher;

    public BookGraphQLController(BookRepository repo, BookPublisher bookPublisher) {
        this.repo = repo;
        this.bookPublisher = bookPublisher;
    }

    @QueryMapping
    public List<Book> books() {
        return repo.findAll();
    }

    @QueryMapping
    public List<Book> booksByAvailability(@Argument Boolean available) {
        if (available == null) {
            return repo.findAll();
        }
        return repo.findByAvailable(available);
    }


    @QueryMapping
    public Book book(@Argument String id) {
        return repo.findById(id).orElseThrow();
    }


    @MutationMapping
    public Book createBook(@Argument String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setAvailable(true);
        Book saved = repo.save(book);

        String message = "Utworzono książkę: " + saved.getTitle() + " (ID: " + saved.getId() + ")";
        bookPublisher.sendMessage(message);

        return saved;
    }

    @MutationMapping
    public Book setAvailability(@Argument String id, @Argument boolean available) {
        Book book = repo.findById(id).orElseThrow();
        book.setAvailable(available);
        Book saved = repo.save(book);

        String action = available ? "oddana" : "wypożyczona";
        String message = "Książka " + saved.getTitle() + " (ID: " + saved.getId() + ") została " + action;
        bookPublisher.sendMessage(message);

        return saved;
    }

    @MutationMapping
    public Boolean deleteBook(@Argument String id) {
        Book book = repo.findById(id).orElseThrow();
        repo.deleteById(id);

        String message = "Usunięto książkę o ID: " + id;
        bookPublisher.sendMessage(message);

        return true;
    }

}
