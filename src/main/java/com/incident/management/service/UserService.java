package com.incident.management.service;

import com.incident.management.dto.UserDTO;
import com.incident.management.entity.User;
import com.incident.management.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User createdUser = userRepository.save(user);
        UserDTO createdUserDTO = new UserDTO();
        BeanUtils.copyProperties(createdUser, createdUserDTO);
        return createdUserDTO;
    }

    public Optional<UserDTO> getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user.get(), userDTO);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }

    public Iterable<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public UserDTO updateUser(Integer userId, UserDTO updatedUserDTO) {
        return userRepository.findById(userId).map(user -> {
            BeanUtils.copyProperties(updatedUserDTO, user);
            User updatedUser = userRepository.save(user);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(updatedUser, userDTO);
            return userDTO;
        }).orElseThrow(() -> new RuntimeException("Users not found"));
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
