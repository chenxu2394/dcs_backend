package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "order", schema = "ecommerce")
public class Order {
  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NonNull
  private User user;

  @NonNull
  private List<Product> products;

  @NonNull
  private String city;

  @NonNull
  private String street;

  @NonNull
  private String post_number;
}
