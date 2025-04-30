package com.realestate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Example: ROLE_ADMIN, ROLE_AGENT, ROLE_SELLER, ROLE_BUYER
}
