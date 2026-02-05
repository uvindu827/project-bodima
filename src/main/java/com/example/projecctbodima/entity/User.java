package com.example.projecctbodima.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fName;
    private String lName;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;
    private String phone;
}
