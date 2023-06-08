package com.example.controllers;

import com.example.dto.NewStudentDto;
import com.example.models.Student;
import com.example.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NewStudentDto studentDto, @RequestParam("parent-id")Long parentId,
                                 @RequestParam("class-id")Long classId) {
        studentService.addStudent(studentDto, parentId, classId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<?> delete(@PathVariable("student-id")Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<?> update(NewStudentDto studentDto, @PathVariable("student-id")Long studentId) {
        studentService.updateStudent(studentDto, studentId);
        return ResponseEntity.accepted().build();
    }
}
