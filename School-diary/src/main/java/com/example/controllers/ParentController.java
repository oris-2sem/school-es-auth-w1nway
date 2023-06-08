package com.example.controllers;

import com.example.dto.NewParentDto;
import com.example.models.Parent;
import com.example.services.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<List<Parent>> getAll() {
        return ResponseEntity.ok(parentService.getParents());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NewParentDto parentDto) {
        parentService.addParent(parentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{parent-id}")
    public ResponseEntity<?> delete(@PathVariable("parent-id")Long parentId) {
        parentService.deleteParent(parentId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{parent-id}")
    public ResponseEntity<?> update(NewParentDto parentDto, @PathVariable("parent-id")Long parentId) {
        parentService.updateParent(parentDto, parentId);
        return ResponseEntity.accepted().build();
    }
}
