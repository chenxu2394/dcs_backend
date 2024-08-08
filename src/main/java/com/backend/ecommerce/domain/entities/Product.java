package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();
}
