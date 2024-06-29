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
@Table(schema = "e_learning", name = "resource")
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