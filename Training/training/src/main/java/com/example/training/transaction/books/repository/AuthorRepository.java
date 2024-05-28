package com.example.training.transaction.books.repository;

import com.example.training.transaction.books.dto.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}