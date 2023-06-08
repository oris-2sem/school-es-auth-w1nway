package com.example.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        STUDENT, TEACHER
    }

}
