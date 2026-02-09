package com.example.projecctbodima.service.impl;

import com.example.projecctbodima.dto.UserDTO
;
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
import org.springframework.web.bind.annotation.PathVariable;

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
    public UserDTO saveUser(UserDTO userDTO) {
        //convert UserDTOto entity
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
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id" + id));

        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public Long deleteUser(Long id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return id;
        }else{
            throw new RuntimeException("User not found by id" + id);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id " + id));

        if(userDTO.getFName() != null){
            existingUser.setFName(userDTO.getFName());
        }
        if(userDTO.getLName() != null){
            existingUser.setLName(userDTO.getLName());
        }
        if(userDTO.getEmail() != null){
            existingUser.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPhone() != null){
            existingUser.setPhone(userDTO.getPhone());
        }

        User updatedUser = userRepo.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}
