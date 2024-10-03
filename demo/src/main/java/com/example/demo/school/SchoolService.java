package com.example.demo.school;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto createSchoolService(SchoolDto schoolDto) {
        var school = schoolMapper.toSchool(schoolDto);

        schoolRepository.save(school);
        return schoolDto;
    }

    public List<SchoolDto> findAllSchoolServices() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .toList();
    }
}
