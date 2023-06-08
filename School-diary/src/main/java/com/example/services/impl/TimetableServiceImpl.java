package com.example.services.impl;

import com.example.dto.NewTimetableDto;
import com.example.models.Class;
import com.example.models.Teacher;
import com.example.models.Timetable;
import com.example.repository.ClassRepository;
import com.example.repository.TeacherRepository;
import com.example.repository.TimetableRepository;
import com.example.services.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Timetable> getTimetableRecords() {
        return timetableRepository.findAll();
    }

    @Override
    public void addTimetableRecord(NewTimetableDto newTimetableDto, Long teacherId, Long classId) {
        Class currClass = classRepository.findById(classId).orElseThrow();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        timetableRepository.save(Timetable.builder()
                        .dateTime(newTimetableDto.getDateTime())
                        .room(newTimetableDto.getRoom())
                        .teacher(teacher)
                        .schoolClass(currClass)
                        .build());
    }

    @Override
    public void deleteTimetableRecord(Long timetableId) {
        timetableRepository.deleteById(timetableId);
    }

    @Override
    public void updateTimetableRecord(NewTimetableDto timetableDto, Long timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId).orElseThrow();
        timetable.setRoom(timetableDto.getRoom());
        timetable.setDateTime(timetableDto.getDateTime());
        timetableRepository.save(timetable);
    }
}
