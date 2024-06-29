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
@Entity
@Table(schema = "e_learning", name = "course")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    // 'Course' <-> 'Author' relationship
    // 'Course' would be the owner of relationship and has join table
    @ManyToMany
    @JoinTable(
            schema = "e_learning",
            name = "authors_courses",
            // It will hold the primary key of the owner of relationship
            joinColumns = { @JoinColumn(name = "course_id") },
            // It will hold the primary key of the inverse of relationship
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private List<Author> authors;

    // 'Course' <-> 'Section' relationship
    // 'Course' would be the inverse of relationship and doesn't have fk
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}