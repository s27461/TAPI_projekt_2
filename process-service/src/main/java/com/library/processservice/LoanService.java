package com.library.processservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository repo;
    private final RestTemplate restTemplate;

    private final String BOOK_SERVICE_URL = "http://resource-service:8081/api/books";

    public LoanService(LoanRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    public Loan loanBook(String bookId, String userId) {
        BookDto book = restTemplate.getForObject(BOOK_SERVICE_URL + "/" + bookId, BookDto.class);

        if (book == null || !book.isAvailable()) {
            throw new RuntimeException("Book not available");
        }

        book.setAvailable(false);
        restTemplate.put(BOOK_SERVICE_URL + "/" + bookId + "/availability?available=false", null);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUserId(userId);
        loan.setStatus("ACTIVE");

        return repo.save(loan);
    }

    public Loan returnBook(String loanId) {
        Loan loan = repo.findById(loanId).orElseThrow();

        BookDto book = loan.getBook();
        book.setAvailable(true);

        restTemplate.put(BOOK_SERVICE_URL + "/" + book.getId() + "/availability?available=true", null);

        loan.setStatus("RETURNED");
        return repo.save(loan);
    }

    public Loan getById(String id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Loan> getAll() {
        return repo.findAll();
    }

    public List<Loan> getByUser(String userId) {
        return repo.findByUserId(userId);
    }

    public List<Loan> getByBook(String bookId) {
        return repo.findByBookId(bookId);
    }

    public List<Loan> getActiveLoans() {
        return repo.findByStatus("ACTIVE");
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
