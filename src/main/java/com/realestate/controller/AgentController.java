package com.realestate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent")
public class AgentController {

    @Secured("ROLE_AGENT") // Ensuring only users with ROLE_AGENT can access this page
    @GetMapping("/dashboard")
    public String agentDashboard() {
        return "agent_dashboard"; // Ensure that agent_dashboard.html exists in the templates folder
    }
}
