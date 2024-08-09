package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "ecommerce")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private int discount;
    private String description;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();
}
