package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product", schema = "ecommerce")
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
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
