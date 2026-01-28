package com.library.processservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://localhost:3002"
})
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    public Loan loan(@RequestParam String bookId,
                     @RequestParam String userId) {
        return service.loanBook(bookId, userId);
    }

    @GetMapping
    public List<Loan> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Loan getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getByUser(@PathVariable String userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<Loan> getByBook(@PathVariable String bookId) {
        return service.getByBook(bookId);
    }

    @GetMapping("/active")
    public List<Loan> getActive() {
        return service.getActiveLoans();
    }

    @PutMapping("/{id}/return")
    public Loan returnBook(@PathVariable String id) {
        return service.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
