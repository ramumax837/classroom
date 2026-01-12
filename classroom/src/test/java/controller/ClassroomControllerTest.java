package com.example.classroom.controller; //Unit test controller endpoints

import com.example.classroom.entity.Classroom;
import com.example.classroom.service.ClassroomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(com.example.classroom.controller.ClassroomController.class)

public class ClassroomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassroomService service;

    //  Test 1: Get classroom by ID (Happy path)
    @Test
    void testGetClassroomById() throws Exception {

        Classroom c = new Classroom();
        c.setId(1L);
        c.setName("Math");
        c.setTeacher("John");

        when(service.getClassroomById(1L)).thenReturn(c);

        mockMvc.perform(get("/classroom/getbyid/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Math"))
                .andExpect(jsonPath("$.teacher").value("John"));
    }

    //  Test 2: Validation failure (Bad Request)
    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {

        String invalidRequest = """
            {
              "name": "",
              "teacher": "John"
            }
            """;

        mockMvc.perform(
                        post("/classroom")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidRequest)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));
    }
}
