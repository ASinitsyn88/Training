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
// Separate tables will not be created for child classes (See: Video.java, Text.java, File.java)
// Child class properties will be included to this parent class => 'resource' table
// If you want to create separate tables per each child class (See: Video.java, Text.java, File.java) then can use:
// 1. @Inheritance(strategy = InheritanceType.InheritanceType.JOINED) without @DiscriminatorColumn
// Tables will be created for both parent and child classes. Children will not include fields of the parent class
// 2. @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) without @DiscriminatorColumn
// Tables will be created for both parent and child classes. Children will include fields of the parent class
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Table will have resource_type column
// and it will contain the value depending on @DiscriminatorValue in child classes
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