package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order", schema = "ecommerce")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToMany
  @JoinTable(
          name = "order_product",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<Product> products = new ArrayList<>();

  private String city;
  private String street;
  private String post_number;
}
