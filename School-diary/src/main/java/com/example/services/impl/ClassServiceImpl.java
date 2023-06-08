package com.example.services.impl;

import com.example.dto.NewClassDto;
import com.example.models.Class;
import com.example.models.Teacher;
import com.example.repository.ClassRepository;
import com.example.repository.TeacherRepository;
import com.example.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    @Override
    public void addClass(NewClassDto classDto, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        classRepository.save(Class.builder()
                        .classLetter(classDto.getClassLetter())
                        .admissionDate(classDto.getAdmissionDate())
                        .classTeacher(teacher)
                        .build());
    }

    @Override
    public void deleteClass(Long classId) {
        classRepository.deleteById(classId);
    }

    @Override
    public void updateClass(NewClassDto classDto, Long classId) {
        Class aClass = classRepository.findById(classId).orElseThrow();
        aClass.setClassLetter(classDto.getClassLetter());
        aClass.setAdmissionDate(classDto.getAdmissionDate());
        classRepository.save(aClass);
    }
}
