package com.realestate.controller;

import com.realestate.service.EmailService;
import com.realestate.service.UserService;
import com.realestate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class ForgotPasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email not registered!");
            return "forgot-password";
        }

        // Send reset link email (dummy, normally you'd generate a token)
        String resetLink = "http://localhost:8080/auth/reset-password?email=" + email;
        String body = "Click the following link to reset your password: " + resetLink;

        emailService.sendEmail(email, "Password Reset Request", body);

        model.addAttribute("message", "Password reset link sent to your email.");
        return "forgot-password";
    }
}
