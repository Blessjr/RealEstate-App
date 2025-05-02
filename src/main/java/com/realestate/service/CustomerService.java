package com.realestate.service;

import java.util.Set;

public class CustomerService {

    private String username;
    private String password;
    private Set<String> roles; // Set of roles (could be role names or role objects)

    // Constructor
    public CustomerService(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for roles
    public Set<String> getRoles() {
        return roles;
    }

    // Setter for roles
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
