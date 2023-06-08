package com.example.services.impl;

import com.example.dto.NewStudentDto;
import com.example.models.Class;
import com.example.models.Parent;
import com.example.models.Student;
import com.example.repository.ClassRepository;
import com.example.repository.ParentRepository;
import com.example.repository.StudentRepository;
import com.example.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ParentRepository parentRepository;
    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(NewStudentDto studentDto, Long parentId, Long classId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        Class aClass = classRepository.findById(classId).orElseThrow();
        studentRepository.save(Student.builder()
                        .firstName(studentDto.getFirstName())
                        .lastName(studentDto.getLastName())
                        .parent(parent)
                        .aClass(aClass)
                        .build());
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void updateStudent(NewStudentDto studentDto, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        studentRepository.save(student);
    }
}
