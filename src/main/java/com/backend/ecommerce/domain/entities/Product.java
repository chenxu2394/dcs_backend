package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    @Column(columnDefinition = "numeric(18,2)")
    private float price;
    private int discount;
    private String description;
    private UUID category_Id;
}
