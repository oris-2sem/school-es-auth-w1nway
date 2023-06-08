package com.example.controllers;

import com.example.dto.NewClassDto;
import com.example.models.Class;
import com.example.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<List<Class>> getAll() {
        return ResponseEntity.ok(classService.getClasses());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NewClassDto classDto, @RequestParam("teacher-id") Long teacherId) {
        classService.addClass(classDto, teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{class-id}")
    public ResponseEntity<?> delete(@PathVariable("class-id")Long classId) {
        classService.deleteClass(classId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{class-id}")
    public ResponseEntity<?> update(NewClassDto classDto, @PathVariable("class-id")Long classId) {
        classService.updateClass(classDto, classId);
        return ResponseEntity.accepted().build();
    }
}
