package com.realestate.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Property> properties;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    // Getters and Setters
}
