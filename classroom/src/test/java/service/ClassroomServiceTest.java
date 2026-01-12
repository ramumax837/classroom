package com.example.classroom.service; //Unit test service logic

import com.example.classroom.entity.Classroom;
import com.example.classroom.exception.ResourceNotFoundException;
import com.example.classroom.repository.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceTest {

    @Mock
    private ClassroomRepository repository;

    @InjectMocks
    private com.example.classroom.service.ClassroomService service;

    //  Test 1: Get classroom by ID (Happy path)
    @Test
    void shouldReturnClassroomWhenFound() {

        Classroom c = new Classroom();
        c.setId(1L);
        c.setName("Math");
        c.setTeacher("John");

        when(repository.findById(1L)).thenReturn(Optional.of(c));

        Classroom result = service.getClassroomById(1L);

        assertEquals("Math", result.getName());
        assertEquals("John", result.getTeacher());
    }

    //  Test 2: Classroom not found (Exception case)
    @Test
    void shouldThrowExceptionWhenClassroomNotFound() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getClassroomById(99L));
    }
}
