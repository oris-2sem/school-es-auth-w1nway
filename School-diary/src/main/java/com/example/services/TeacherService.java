package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewTeacherDto;
import com.example.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    void addTeacher(NewTeacherDto teacherDto);

    void deleteTeacher(Long teacherId);

    void updateTeacher(NewTeacherDto teacherDto, Long teacherId);
}
