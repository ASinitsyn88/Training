package com.example.training.transaction.books.service;

import com.example.training.transaction.books.dto.Book;
import java.util.Optional;

public interface BookService {
    boolean isBookExists(Book book);
    Book save(Book book);
    Optional<Book> findById(String isbn);
    Iterable<Book> listBooks();
    void deleteBookById(String isbn);
}