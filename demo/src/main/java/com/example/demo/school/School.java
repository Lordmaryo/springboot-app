package com.example.demo.school;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference // parent to prevent infinite loop
    private List<Student> students;

    public School(List<Student> students) {
        this.students = students;
    }

    public School(List<Student> students, String name) {
        this.students = students;
        this.name = name;
    }

    // DTO constructor
    public School(String name) {
        this.name = name;
    }

    public School() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
