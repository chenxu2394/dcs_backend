package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_product", schema = "ecommerce")
public class OrderProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name="order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;

  @Column(name="price")
  private double price;
}
