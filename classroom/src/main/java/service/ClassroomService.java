package com.example.classroom.service;

import com.example.classroom.entity.Classroom;
import com.example.classroom.repository.ClassroomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository repository;

    public ClassroomService(ClassroomRepository repository) {
        this.repository = repository;
    }

    // ---------------- BASIC CRUD ---------------- //

    // Add a new classroom
    public Classroom addClassroom(Classroom classroom) {
        return repository.save(classroom);
    }

    // Get all classrooms
    public List<Classroom> getAllClassrooms() {
        return repository.findAll();
    }

    // Get a classroom by ID
    public Classroom getClassroomById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new com.example.classroom.exception.ResourceNotFoundException("Classroom not found with id " + id));
    }


    // Update a classroom
    public Classroom updateClassroom(Classroom classroom) {
        Classroom existing = repository.findById(classroom.getId()).orElse(null);
        if (existing == null) return null;

        existing.setName(classroom.getName());
        existing.setTeacher(classroom.getTeacher());

        return repository.save(existing);
    }

    // Delete a classroom by ID
    public void deleteClassroom(Long id) {
        repository.deleteById(id);
    }

    // ---------------- ADVANCED QUERY METHODS ---------------- //

    // Get classrooms by teacher
    public List<Classroom> getByTeacher(String teacher) {
        return repository.findByTeacher(teacher);
    }

    // Search classrooms by name containing keyword
    public List<Classroom> searchByName(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    // Get classrooms by teacher and exact name
    public List<Classroom> getByTeacherAndName(String teacher, String name) {
        return repository.findByTeacherAndName(teacher, name);
    }

    // ---------------- PAGINATION & SORTING ---------------- //

    // Get classrooms with pagination and sorting
    public Page<Classroom> getClassrooms(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
