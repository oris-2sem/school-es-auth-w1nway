package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewTimetableDto;
import com.example.models.Class;
import com.example.models.Timetable;

import java.util.List;

public interface TimetableService {
    List<Timetable> getTimetableRecords();

    void addTimetableRecord(NewTimetableDto timetableDto, Long teacherId, Long classId);

    void deleteTimetableRecord(Long timetableId);

    void updateTimetableRecord(NewTimetableDto timetableDto, Long timetableId);
}
