package com.example.classroom.repository; //Unit test DB queries

import com.example.classroom.entity.Classroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class ClassroomRepositoryTest {

    @Autowired
    private com.example.classroom.repository.ClassroomRepository repository;

    @Test
    public void findByTeacherShouldReturnClassrooms() {
        Classroom c = new Classroom(null, "Math", "John");
        repository.save(c);

        List<Classroom> list = repository.findByTeacher("John");
        assertFalse(list.isEmpty());
    }
}
