package com.example.onlineFoodOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}

