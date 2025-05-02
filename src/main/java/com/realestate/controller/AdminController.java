package com.realestate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN") // Ensuring only users with ROLE_ADMIN can access this page
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; // Ensure that admin_dashboard.html exists in the templates folder
    }
}
