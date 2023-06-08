package com.example.services;

import com.example.dto.NewClassDto;
import com.example.models.Class;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassService {
    List<Class> getClasses();

    void addClass(NewClassDto classDto, Long teacherId);

    void deleteClass(Long classId);

    void updateClass(NewClassDto classDto, Long classId);
}
