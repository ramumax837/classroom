package com.example.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //  MATCHES DB column: teacher
    @Column(name = "teacher", length = 100)
    private String teacher;

    //  MATCHES DB column: name
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public Classroom() {
    }

    public Classroom(Long id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }
}
