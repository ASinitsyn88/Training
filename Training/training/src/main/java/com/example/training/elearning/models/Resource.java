package com.example.training.elearning.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "e_learning", name = "resource")
public class Resource extends BaseEntity {
    private String name;
    private int size;
    private String url;

    // 'Resource' <-> 'Lecture' relationship
    // Both 'Resource' and 'Lecture' would be the owners of relationship and has fk (bidirectional)
    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}