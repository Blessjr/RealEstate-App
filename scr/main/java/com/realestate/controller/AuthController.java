package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.service.AuthService;
import com.realestate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        authService.registerUser(user);
        emailService.sendWelcomeEmail(user.getEmail());
        model.addAttribute("message", "Registration Successful! Please Login.");
        return "login";
    }
}
