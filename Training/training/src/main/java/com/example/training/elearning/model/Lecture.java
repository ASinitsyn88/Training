package com.example.training.elearning.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "e_learning", name = "lecture")
public class Lecture extends BaseEntity {
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