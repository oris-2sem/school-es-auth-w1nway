package com.example.controllers;

import com.example.dto.NewParentDto;
import com.example.dto.NewTeacherDto;
import com.example.models.Parent;
import com.example.models.Teacher;
import com.example.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NewTeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{teacher-id}")
    public ResponseEntity<?> delete(@PathVariable("teacher-id")Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{teacher-id}")
    public ResponseEntity<?> update(NewTeacherDto teacherDto, @PathVariable("teacher-id")Long teacherId) {
        teacherService.updateTeacher(teacherDto, teacherId);
        return ResponseEntity.accepted().build();
    }
}
