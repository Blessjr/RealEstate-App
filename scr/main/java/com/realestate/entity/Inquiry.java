package com.realestate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private Property property;
}
