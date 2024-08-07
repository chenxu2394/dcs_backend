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
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int discount;
    private String description;
    private int category_Id;
}
