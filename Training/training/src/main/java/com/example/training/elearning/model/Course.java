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
@Entity
@Table(schema = "e_learning", name = "course")
public class Course extends BaseEntity {
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