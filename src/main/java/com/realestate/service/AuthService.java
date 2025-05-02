package com.realestate.service;

import com.realestate.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String authenticateUser(String username, String password);
    User getLoggedInUser();
    String generateToken(User user);
    boolean validateToken(String token, UserDetails userDetails);
    String extractUsername(String token);
    void registerUser(User user);
}