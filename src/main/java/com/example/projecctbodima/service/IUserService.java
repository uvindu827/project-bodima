package com.example.projecctbodima.service;

import com.example.projecctbodima.dto.userDTO;

import java.util.List;

public interface IUserService {
    userDTO saveUser(userDTO userDTO);
    List<userDTO> getAllUsers();
    userDTO getUsetById(Long id);
    Long deleteUser(Long id);
}
