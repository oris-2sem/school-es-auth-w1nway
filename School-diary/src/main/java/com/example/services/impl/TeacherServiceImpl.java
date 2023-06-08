package com.example.services.impl;

import com.example.dto.NewTeacherDto;
import com.example.models.Teacher;
import com.example.repository.TeacherRepository;
import com.example.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void addTeacher(NewTeacherDto teacherDto) {
        teacherRepository.save(Teacher.builder()
                        .firstName(teacherDto.getFirstName())
                        .lastName(teacherDto.getLastName())
                        .profile(teacherDto.getProfile())
                        .build());
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public void updateTeacher(NewTeacherDto teacherDto, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setProfile(teacherDto.getProfile());
        teacherRepository.save(teacher);
    }
}
