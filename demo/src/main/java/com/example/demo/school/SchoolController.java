package com.example.demo.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/school")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(@RequestBody SchoolDto schoolDto) {
        return schoolService.createSchoolService(schoolDto);
    }

    @GetMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDto> findAll() {
        return schoolService.findAllSchoolServices();
    }
}
