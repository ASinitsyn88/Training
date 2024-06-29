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
@Table(schema = "e_learning", name = "section")
public class Section {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int sectionOrder;

    // 'Section' <-> 'Course' relationship
    // 'Section' would be the owner of relationship and has fk
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // 'Section' <-> 'Lecture' relationship
    // 'Section' would be the inverse of relationship and doesn't have fk
    @OneToMany
    private List<Lecture> lectures;
}