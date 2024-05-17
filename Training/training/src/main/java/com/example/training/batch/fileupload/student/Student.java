package com.example.training.batch.fileupload.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity that should be persisted in the database
 */
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_generator")
    @SequenceGenerator(name = "student_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Integer id;
    private String firstname;
    private String lastname;
    private int age;
}