package com.example.projecctbodima.service.impl;

import com.example.projecctbodima.dto.userDTO;
import com.example.projecctbodima.entity.User;
import com.example.projecctbodima.repository.IUserRepo;
import com.example.projecctbodima.service.IUserService;
import jakarta.transaction.Transactional;
import org.apache.catalina.UserDatabase;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public userDTO saveUser(userDTO userDTO) {
        //convert userDTO to entity
        User user = new User();
        user.setFName(userDTO.getFName());
        user.setLName(userDTO.getLName());
        user.setEmail(userDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);

        user.setRole(userDTO.getRole());
        user.setPhone(userDTO.getPhone());

        //save entity in database
        User savedUser = userRepo.save(user);

        userDTO.setId(savedUser.getId());
        userDTO.setPassword("******");
        return userDTO;
    }

    @Override
    public List<userDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<userDTO>>(){}.getType());

    }

    @Override
    public userDTO getUsetById(Long id) {
        return null;
    }

    @Override
    public Long deleteUser(Long id) {
        return id;
    }
}
