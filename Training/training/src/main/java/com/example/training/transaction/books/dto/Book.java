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
@Entity
@Table(name = "books")
public class Book {
    @Id
    private String isbn;
    private String title;
    // book table will have author_id column in which it will store author identifier
    // orm will transform it to the Author class
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}