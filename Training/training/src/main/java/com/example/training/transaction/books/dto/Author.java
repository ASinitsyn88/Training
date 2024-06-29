package com.example.training.transaction.books.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bookAuthor") // Set unique entity name because there are different entities with the same name
@Table(name = "authors")
public class Author {
    @Id
    @SequenceGenerator(name="authors_generator", sequenceName="authors_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_generator")
    private Long id;

    /**
     * Must be less than 20 chars!
     * But we're not going to enforce this with validation.
     * Why? Because it's a contrived example!
     */
    private String name;

    private Integer age;
}