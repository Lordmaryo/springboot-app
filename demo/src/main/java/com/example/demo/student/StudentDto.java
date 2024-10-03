package com.example.demo.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "Firstname must not be empty")
        String firstname,
        @NotEmpty(message = "Lastname must not be empty")
        String lastname,
        @NotEmpty(message = "Email must not be empty")
        String email,
        Integer schoolId
) {
}
