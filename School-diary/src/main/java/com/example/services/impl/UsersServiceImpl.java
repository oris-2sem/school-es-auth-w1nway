package com.example.services.impl;

import com.example.dto.SignUpDto;
import com.example.models.Student;
import com.example.models.Teacher;
import com.example.models.User;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import com.example.repository.UsersRepository;
import com.example.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    @Override
    public void signUp(SignUpDto signUpDto) {
        if(usersRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new RuntimeException("Account with " + signUpDto.getEmail() + " email is present");
        }
        if(signUpDto.getIsTeacher()) {
            teacherRepository.save(Teacher.builder()
                            .firstName(signUpDto.getFirstName())
                            .lastName(signUpDto.getLastName())
                    .build());
        } else {
            studentRepository.save(Student.builder()
                            .firstName(signUpDto.getFirstName())
                            .lastName(signUpDto.getLastName())
                    .build());
        }
        User user = User.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .email(signUpDto.getEmail())
                .role(signUpDto.getIsTeacher() ? User.Role.TEACHER : User.Role.STUDENT)
                .hashPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        usersRepository.save(user);
    }
}
