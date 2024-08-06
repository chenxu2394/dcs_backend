package com.backend.ecommerce.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public UUID id;

  @Column(nullable = false)
  public User user;

  @Column(nullable = false)
  public List<Product> products;

  @Column(nullable = false)
  public String city;

  @Column(nullable = false)
  public String street;

  @Column(nullable = false)
  public String post_number;
}
