package com.library.resourceservice;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://localhost:3002"
})
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable String id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        book.setAvailable(true);
        return repo.save(book);
    }

    @PutMapping("/{id}/availability")
    public Book setAvailability(@PathVariable String id,
                                @RequestParam boolean available) {
        Book book = repo.findById(id).orElseThrow();
        book.setAvailable(available);
        return repo.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
