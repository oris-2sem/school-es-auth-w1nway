package com.example.services.impl;

import com.example.dto.NewClassDto;
import com.example.dto.NewLessonDto;
import com.example.models.Lesson;
import com.example.models.Timetable;
import com.example.repository.LessonRepository;
import com.example.repository.TimetableRepository;
import com.example.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TimetableRepository timetableRepository;

    @Override
    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public void addLesson(NewLessonDto lessonDto, Long timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId).orElseThrow();
        lessonRepository.save(Lesson.builder()
                        .subject(lessonDto.getSubject())
                        .theme(lessonDto.getTheme())
                        .timetable(timetable)
                        .build());
    }

    @Override
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @Override
    public void updateLesson(NewLessonDto classDto, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        lesson.setSubject(classDto.getSubject());
        lesson.setTheme(classDto.getTheme());
        lessonRepository.save(lesson);
    }
}
