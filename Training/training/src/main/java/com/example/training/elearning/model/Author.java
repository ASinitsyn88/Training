package com.example.training.elearning.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "courseAuthor") // Set unique entity name because there are different entities with the same name
@Table(schema = "e_learning", name = "author")
public class Author extends BaseEntity {
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