package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewStudentDto;
import com.example.models.Class;
import com.example.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    void addStudent(NewStudentDto studentDto, Long parentId, Long classId);

    void deleteStudent(Long studentId);

    void updateStudent(NewStudentDto studentDto, Long studentId);
}
