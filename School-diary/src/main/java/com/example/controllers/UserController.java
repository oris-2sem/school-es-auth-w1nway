package com.example.controllers;

import com.example.dto.SignUpDto;
import com.example.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody SignUpDto signUpDto) {
        usersService.signUp(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
