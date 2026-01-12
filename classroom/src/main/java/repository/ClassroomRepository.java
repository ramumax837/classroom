package com.example.classroom.repository;

import com.example.classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    // 1 Derived Query — Find by teacher
    List<Classroom> findByTeacher(String teacher);

    // 2 Derived Query — Find name contains text
    List<Classroom> findByNameContaining(String name);

    // 3 JPQL Query — Name contains & teacher match
    @Query("""
           SELECT c FROM Classroom c
           WHERE c.name LIKE %:name% AND c.teacher = :teacher
           """)
    List<Classroom> searchByNameAndTeacher(@Param("name") String name,
                                           @Param("teacher") String teacher);

    List<com.example.classroom.entity.Classroom> findByTeacherAndName(String teacher, String name);
}
