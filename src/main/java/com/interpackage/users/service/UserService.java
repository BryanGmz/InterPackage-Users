package com.interpackage.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.interpackage.users.model.User;
import com.interpackage.users.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
