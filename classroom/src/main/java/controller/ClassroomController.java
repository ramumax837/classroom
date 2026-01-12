package com.example.classroom.controller;

import com.example.classroom.entity.Classroom;
import com.example.classroom.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService service;


    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    // ---------------- BASIC CRUD ---------------- //

    @PostMapping
    public ResponseEntity<Classroom> addClassroom(
            @Valid @RequestBody com.example.classroom.dto.ClassroomDTO dto) {

        Classroom classroom = new Classroom();
        classroom.setName(dto.getName());
        classroom.setTeacher(dto.getTeacher());

        Classroom savedClassroom = service.addClassroom(classroom);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedClassroom);
    }



    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return service.getAllClassrooms();
    }

    @GetMapping("/getbyid/{id}")
    public Classroom getClassroom(@PathVariable Long id) {
        return service.getClassroomById(id);
    }

    @PutMapping("/update")
    public Classroom updateClassroom(@RequestBody Classroom classroom) {
        return service.updateClassroom(classroom);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        service.deleteClassroom(id);
    }

    // ---------------- ADVANCED QUERIES ---------------- //

    @GetMapping("/teacher/{teacher}")
    public List<Classroom> getByTeacher(@PathVariable String teacher) {
        return service.getByTeacher(teacher);
    }

    @GetMapping("/search")
    public List<Classroom> searchByName(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    @GetMapping("/filter")
    public List<Classroom> getByTeacherAndName(@RequestParam String teacher,
                                               @RequestParam String name) {
        return service.getByTeacherAndName(teacher, name);
    }

    // ---------------- PAGINATION & SORTING ---------------- //

    @GetMapping("/paged")
    public Page<Classroom> getClassroomsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getClassrooms(pageable);
    }
}
