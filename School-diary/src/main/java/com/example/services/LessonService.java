package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewLessonDto;
import com.example.models.Class;
import com.example.models.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getLessons();

    void addLesson(NewLessonDto lessonDto, Long timetableId);

    void deleteLesson(Long lessonId);

    void updateLesson(NewLessonDto classDto, Long lessonId);
}
