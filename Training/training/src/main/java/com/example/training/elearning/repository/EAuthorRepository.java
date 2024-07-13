package com.example.training.elearning.repository;

import com.example.training.elearning.model.Author;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EAuthorRepository extends CrudRepository<Author, Integer> {
    @Transactional
    List<Author> findByNamedQuery(@Param("age") int age); // Named query

    @Modifying
    @Transactional
    void updateByNamedQuery(@Param("age") int age); // Named query

    @Modifying
    @Transactional
    @Query("update com.example.training.elearning.model.Author a set a.age = :age where a.id = :id")
    int updateAuthor(int age, int id);

    List<Author> findAllByFirstName(String fn);
    List<Author> findAllByFirstNameIgnoreCase(String fn);
    List<Author> findAllByFirstNameContainingIgnoreCase(String fn);
    List<Author> findAllByFirstNameStartsWithIgnoreCase(String fn);
    List<Author> findAllByFirstNameEndsWithIgnoreCase(String fn);
    List<Author> findAllByFirstNameInIgnoreCase(List<String> firstNames);
}