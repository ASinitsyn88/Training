package com.example.training.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "e_learning", name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    // 'Lecture' <-> 'Section' relationship
    // 'Lecture' would be the owner of relationship and has fk
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    // 'Lecture' <-> 'Resource' relationship
    // Both 'Lecture' and 'Resource' would be the owners of relationship and has fk (bidirectional)
    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}