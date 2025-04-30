package com.realestate.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboardHome(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login"; // If not authenticated, go to login
        }

        // Redirect based on ROLE
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            switch (role) {
                case "ROLE_ADMIN":
                    return "redirect:/admin/dashboard";
                case "ROLE_AGENT":
                    return "redirect:/agent/dashboard";
                case "ROLE_SELLER":
                    return "redirect:/seller/dashboard";
                case "ROLE_BUYER":
                    return "redirect:/buyer/dashboard";
                default:
                    break;
            }
        }

        // If role is unknown
        return "redirect:/auth/login";
    }
}
