package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.service.AuthService;
import com.realestate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    // Render the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Render the signup page with an empty user model
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // Handle form submission for user registration
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        // If validation fails, return to the signup page
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            // Attempt to register the user and send email
            authService.registerUser(user);  // Ensure this method is in the AuthService class
            emailService.sendWelcomeEmail(user.getEmail());
            model.addAttribute("message", "Registration successful! Please log in.");
            return "login";
        } catch (Exception e) {
            // Handle registration errors
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "signup";
        }
    }
}

