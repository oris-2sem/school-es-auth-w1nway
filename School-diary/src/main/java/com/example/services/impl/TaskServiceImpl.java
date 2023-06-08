package com.example.services.impl;

import com.example.dto.NewTaskDto;
import com.example.models.Lesson;
import com.example.models.Student;
import com.example.models.Task;
import com.example.repository.LessonRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TaskRepository;
import com.example.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addTask(NewTaskDto taskDto, Long studentId, Long lessonId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();

        taskRepository.save(Task.builder()
                        .commentary(taskDto.getCommentary())
                        .description(taskDto.getDescription())
                        .mark(taskDto.getMark())
                        .type(Task.Type.valueOf(taskDto.getType()))
                        .student(student)
                        .lesson(lesson)
                        .build());
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void updateTask(NewTaskDto taskDto, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setCommentary(taskDto.getCommentary());
        task.setDescription(taskDto.getDescription());
        task.setMark(taskDto.getMark());
        task.setType(Task.Type.valueOf(taskDto.getType()));
        taskRepository.save(task);
    }
}
