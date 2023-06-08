package com.example.services;

import com.example.dto.NewClassDto;
import com.example.dto.NewParentDto;
import com.example.models.Class;
import com.example.models.Parent;

import java.util.List;

public interface ParentService{
    List<Parent> getParents();

    void addParent(NewParentDto parentDto);

    void deleteParent(Long parentId);

    void updateParent(NewParentDto parentDto, Long parentId);
}
