package com.example.training.transaction.books.repository;

import com.example.training.transaction.books.dto.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {

}