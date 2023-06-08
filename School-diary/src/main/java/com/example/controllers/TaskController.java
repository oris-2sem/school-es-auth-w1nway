package com.example.controllers;

import com.example.dto.NewParentDto;
import com.example.dto.NewTaskDto;
import com.example.models.Parent;
import com.example.models.Task;
import com.example.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NewTaskDto taskDto, @RequestParam("student-id")Long studentId,
                                 @RequestParam("lesson-id")Long lessonId) {
        taskService.addTask(taskDto, studentId, lessonId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<?> delete(@PathVariable("task-id")Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<?> update(NewTaskDto taskDto, @PathVariable("task-id")Long taskId) {
        taskService.updateTask(taskDto, taskId);
        return ResponseEntity.accepted().build();
    }
}
