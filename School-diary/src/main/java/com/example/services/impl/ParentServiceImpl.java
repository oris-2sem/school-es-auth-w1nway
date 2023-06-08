package com.example.services.impl;

import com.example.dto.NewClassDto;
import com.example.dto.NewParentDto;
import com.example.models.Parent;
import com.example.repository.ParentRepository;
import com.example.services.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public List<Parent> getParents() {
        return parentRepository.findAll();
    }

    @Override
    public void addParent(NewParentDto parentDto) {
        parentRepository.save(Parent.builder()
                        .profile(parentDto.getProfile())
                        .build());
    }

    @Override
    public void deleteParent(Long parentId) {
        parentRepository.deleteById(parentId);
    }

    @Override
    public void updateParent(NewParentDto parentDto, Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.setProfile(parentDto.getProfile());
        parentRepository.save(parent);
    }
}
