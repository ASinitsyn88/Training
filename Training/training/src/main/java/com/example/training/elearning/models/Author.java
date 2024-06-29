package com.example.training.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "courseAuthor") // Set unique entity name because there are different entities with the same name
@Table(schema = "e_learning", name = "author")
public class Author {
    @Id
    @GeneratedValue
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;

    // 'Author' <-> 'Course' relationship
    // 'Author' would be the inverse of relationship and doesn't have join table
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}