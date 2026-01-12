package com.example.classroom.config; //Setup security for tests

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigTest {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // All endpoints require authentication
                )
                .httpBasic(); // Enable basic authentication

        return http.build();
    }
}
