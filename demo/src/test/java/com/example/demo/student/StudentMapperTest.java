package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void ShouldMapStudentDtoToStudent() {
        StudentDto studentDto = new StudentDto(
                "John",
                "Doe",
                "johndoe@mail.com",
                1);

        Student student = mapper.toStudent(studentDto);

        assertEquals(studentDto.firstname(), student.getFirstname());
        assertEquals(studentDto.lastname(), student.getLastname());
        assertEquals(studentDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentDtoIsNull() {
        var exception = assertThrows(NullPointerException.class,
                () -> mapper.toStudent(null));
        assertEquals("The studentDto should not be null", exception.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student(
                "Ebube",
                "emmanuel",
                "ebube@gmail.com",
                21
        );

        //When
        StudentResponseDto responseDto = mapper.toStudentResponseDto(student);

        //Then
        assertEquals(student.getFirstname(), responseDto.firstname());
        assertEquals(student.getLastname(), responseDto.lastname());
        assertEquals(student.getEmail(), responseDto.email());
    }
}
