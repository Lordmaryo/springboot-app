package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper mapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void ShouldSuccessfullySaveAStudent() {
        //Given
        StudentDto studentDto = new StudentDto(
                "Ebube",
                "emmanuel",
                "ebube@gmail.com",
                1
        );

        Student student = new Student(
                "Ebube",
                "emmanuel",
                "ebube@gmail.com",
                21
        );
        Student savedStudent = new Student(
                "Ebube",
                "emmanuel",
                "ebube@gmail.com",
                21
        );
        savedStudent.setId(1);

        //When
        when(mapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(mapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto(
                "Ebube",
                "emmanuel",
                "ebube@gmail.com"
        ));

        StudentResponseDto responseDto = studentService.saveStudentService(studentDto);

        //Then
        assertEquals(studentDto.firstname(), responseDto.firstname());
        assertEquals(studentDto.lastname(), responseDto.lastname());
        assertEquals(studentDto.email(), responseDto.email());

        verify(mapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
        verify(mapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void shouldSuccessfullyFindAllStudents() {
        //Given
        List<Student> students = Arrays.asList(
                new Student(
                        "Ebube",
                        "Emmanuel",
                        "ebube@gmail.com",
                        21
                ),
                new Student(
                        "John",
                        "Doe",
                        "johndoe@mail.com",
                        23
                )
        );
        List<StudentResponseDto> studentsResponseDto = Arrays.asList(
                new StudentResponseDto(
                        "Ebube",
                        "Emmanuel",
                        "ebube@gmail.com"
                ),
                new StudentResponseDto(
                        "John",
                        "Doe",
                        "johndoe@mail.com"
                )
        );

        //When
        when(studentRepository.findAll()).thenReturn(students);
        for (StudentResponseDto dtos : studentsResponseDto) {
            when(mapper.toStudentResponseDto(any(Student.class)))
                    .thenReturn(dtos);
        }

        //Then
        List<StudentResponseDto> responseDtos = studentService.findAllStudentsService();

        //Assert
        assertEquals(students.size(), studentsResponseDto.size());
        assertEquals(students.size(), responseDtos.size());
        for (Student student : students) {
            assertEquals(student.getFirstname(), studentsResponseDto
                    .get(students.indexOf(student)).firstname());

            assertEquals(student.getLastname(), studentsResponseDto
                    .get(students.indexOf(student)).lastname());

            assertEquals(student.getEmail(), studentsResponseDto
                    .get(students.indexOf(student)).email());
        }

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindStudentById() {
        //Given
        int id = 1;
        Student student = new Student(
                "Ebube",
                "Emmanuel",
                "ebube@gmail.com",
                21
        );
        StudentResponseDto studentResponseDto = new StudentResponseDto(
                "Ebube",
                "Emmanuel",
                "ebube@gmail.com"
        );

        //When
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(mapper.toStudentResponseDto(student)).thenReturn(studentResponseDto);

        //Then
        StudentResponseDto responseDto = studentService.findStudentByIdService(id);

        assertEquals(studentResponseDto.firstname(), responseDto.firstname());
        assertEquals(studentResponseDto.lastname(), responseDto.lastname());
        assertEquals(studentResponseDto.email(), responseDto.email());

        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    public void shouldReturnStudentByName() {
        //Given
        String name = "Ebube";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Ebube",
                "Emmanuel",
                "ebube@gmail.com",
                21));

        //When
        when(studentRepository.findAllByFirstnameContaining(name)).thenReturn(students);
        when(mapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Ebube",
                        "Emmanuel",
                        "ebube@gmail.com"
                ));

        //Then
        var responseDto = studentService.findAllStudentsByNameService(name);

        assertEquals(students.size(), responseDto.size());

        verify(studentRepository, times(1))
                .findAllByFirstnameContaining(name);
    }

    @Test
    public void shouldDeleteStudentById() {
        //Given
        int id = 1;

        //When
        studentService.deleteStudentByIdService(id);

        //Then
        verify(studentRepository, times(1)).deleteById(id);
    }
}
