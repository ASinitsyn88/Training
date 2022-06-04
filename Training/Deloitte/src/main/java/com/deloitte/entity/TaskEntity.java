package com.deloitte.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity implements Serializable {

    private final static long serialVersionUID = 201105211237L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", length = 1024, nullable = false)
    private String description;
    @Column(name = "modifytimestamp", nullable = false)
    private LocalDateTime modifytimestamp;
    @Column(name = "completed")
    private boolean completed;
}