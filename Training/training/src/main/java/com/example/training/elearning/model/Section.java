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
@Table(schema = "e_learning", name = "section")
public class Section extends BaseEntity {
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