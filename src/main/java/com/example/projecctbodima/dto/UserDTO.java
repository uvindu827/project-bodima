package com.example.projecctbodima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String role;
    private String phone;
}
