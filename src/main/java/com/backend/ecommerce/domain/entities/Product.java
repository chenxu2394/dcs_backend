package com.backend.ecommerce.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int discount;
    private String description;
}
