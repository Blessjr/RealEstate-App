package com.realestate.service.impl;

import com.realestate.entity.Role;
import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import com.realestate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(User user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role (make sure the Role entity and User's roles field are correctly set up)
        Role userRole = new Role("USER"); // Create a new role with name "USER"
        user.setRoles(Collections.singleton(userRole));  // Set the role

        // Save user
        userRepository.save(user);
    }
}
