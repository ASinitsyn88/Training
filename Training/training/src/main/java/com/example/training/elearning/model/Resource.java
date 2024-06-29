package com.example.training.elearning.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "e_learning", name = "resource")
// It will include all child/inherited class properties to this class
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Table will have resource_type column
// and it will contain the value depending on @DiscriminatorValue
@DiscriminatorColumn(name = "resource_type")
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int size;
    private String url;

    // 'Resource' <-> 'Lecture' relationship
    // Both 'Resource' and 'Lecture' would be the owners of relationship and has fk (bidirectional)
    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}