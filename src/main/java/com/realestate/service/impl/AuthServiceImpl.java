package com.realestate.service.impl;

import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import com.realestate.service.AuthService;
import com.realestate.config.JwtTokenProvider;
import com.realestate.exception.UsernameAlreadyTakenException;
import com.realestate.exception.InvalidEmailFormatException;
import com.realestate.exception.WeakPasswordException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    // Method to register a user with validation and password encoding
    @Override
    public void registerUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // Validate input
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (userRepository.findByUsername(username)) {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        // Optional: validate email format if applicable
        if (user.getEmail() != null && !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailFormatException("Invalid email format");
        }

        // Password strength validation: min 8 chars, 1 upper, 1 lower, 1 digit, 1 special character
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%^&*()\\-_=+]).{8,}$")) {
            throw new WeakPasswordException("Password must be at least 8 characters long and contain uppercase, lowercase, a digit, and a special character");
        }

        // Encode and save
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public String authenticateUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticateUser'");
    }

    @Override
    public User getLoggedInUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLoggedInUser'");
    }

    @Override
    public String generateToken(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateToken'");
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

    @Override
    public String extractUsername(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractUsername'");
    }

    // Implement other methods (authenticate, etc.) if required.
}
