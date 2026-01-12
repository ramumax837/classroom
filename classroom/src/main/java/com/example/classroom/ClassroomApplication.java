package com.example.classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomApplication.class, args);
    }
}

//psql -U postgres
//\c internship_day1;
//GRANT ALL PRIVILEGES ON SCHEMA public TO classroom_user;
//ALTER SCHEMA public OWNER TO classroom_user;