package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewTaskDto;
import com.example.models.Class;
import com.example.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    void addTask(NewTaskDto taskDto, Long studentId, Long lessonId);

    void deleteTask(Long taskId);

    void updateTask(NewTaskDto classDto, Long taskId);
}
