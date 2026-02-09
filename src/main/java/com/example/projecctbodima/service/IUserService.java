package com.example.projecctbodima.service;

import com.example.projecctbodima.dto.UserDTO
;

import java.util.List;

public interface IUserService {
    UserDTO saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    Long deleteUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
}
