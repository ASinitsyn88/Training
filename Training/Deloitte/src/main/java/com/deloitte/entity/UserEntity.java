package com.deloitte.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private final static long serialVersionUID = 201105211236L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", length = 255, unique = true, nullable = false)
    private String login;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = TaskEntity.class)
    @Column(name = "task_id", length = 20)
    private Set<TaskEntity> tasks = new HashSet<>();
}