package com.example.training.transaction.books.controller;

import com.example.training.transaction.books.dto.Book;
import com.example.training.transaction.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PutMapping(path = "{isbn}")
    public ResponseEntity<Book> createUpdateBook(@PathVariable String isbn, @RequestBody Book book) {
        book.setIsbn(isbn);

        final boolean isBookExists = bookService.isBookExists(book);
        final Book savedBook = bookService.save(book);

        return isBookExists ? new ResponseEntity<>(savedBook, OK) : new ResponseEntity<>(savedBook, CREATED);
    }

    @GetMapping(path = "{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable String isbn) {
        return bookService.findById(isbn)
                .map(book -> new ResponseEntity<>(book, OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> listBooks() {
        return new ResponseEntity<>(bookService.listBooks(), OK);
    }

    @DeleteMapping(path = "{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable final String isbn) {
        bookService.deleteBookById(isbn);
        return new ResponseEntity<>(NO_CONTENT);
    }
}