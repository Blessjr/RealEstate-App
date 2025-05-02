package com.realestate.service;

import com.realestate.entity.Role;
import com.realestate.entity.User;
import com.realestate.repository.RoleRepository;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public interface userService {
        User findByEmail(String email);
    }

    public User findByEmail(String email) {
        //Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    public void saveUser(User user) {
        //Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }
    
}
