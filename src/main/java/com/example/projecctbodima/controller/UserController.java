package com.example.projecctbodima.controller;

import com.example.projecctbodima.dto.userDTO;
import com.example.projecctbodima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createuser")
    public userDTO createUser(@RequestBody userDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
