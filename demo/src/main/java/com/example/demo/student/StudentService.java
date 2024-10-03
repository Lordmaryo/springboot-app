package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudentService(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);

        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudentsService() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }

    public StudentResponseDto findStudentByIdService(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findAllStudentsByNameService(String firstname) {
        return studentRepository.findAllByFirstnameContaining(firstname)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }

    public void deleteStudentByIdService(int id) {
        studentRepository.deleteById(id);
    }
}
