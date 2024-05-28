package com.example.training.transaction.books.service;

import com.example.training.transaction.books.dto.Author;
import com.example.training.transaction.books.dto.Book;
import com.example.training.transaction.books.repository.AuthorRepository;
import com.example.training.transaction.books.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    /**
     * WARNING - This is a very VERY contrived example.
     *
     * There are easier ways to code this logic when you're not
     * demonstrating transactions -- take a look as Hibernate cascades!
     */
    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getAuthor() == null) {
            throw new RuntimeException("Author must be provided");
        }
        final Author bookAuthor = book.getAuthor();

        // 1. Save book without author
        book.setAuthor(null);
        final Book savedBook = bookRepository.save(book);

        // 2. Create/Retrieve the Author
        final Author author = (bookAuthor.getId() == null)
                ? authorRepository.save(bookAuthor)
                : authorRepository.findById(bookAuthor.getId()).orElseThrow(() -> new RuntimeException("Author not found"));
        savedBook.setAuthor(author);

        // 3. Save the Book with the Author and return
        return bookRepository.save(savedBook);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Iterable<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean isBookExists(Book book) {
        return bookRepository.existsById(book.getIsbn());
    }

    @Override
    public void deleteBookById(String isbn) {
        try {
            bookRepository.deleteById(isbn);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existing book", e);
        }
    }
}