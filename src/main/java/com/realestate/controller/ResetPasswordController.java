package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model) {
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "User not found!");
            return "reset-password";
        }

        user.setPassword(newPassword); // In production, encode password!
        userService.saveUser(user);

        model.addAttribute("success", "Password reset successfully. Please login.");
        return "redirect:/auth/login";
    }
}
