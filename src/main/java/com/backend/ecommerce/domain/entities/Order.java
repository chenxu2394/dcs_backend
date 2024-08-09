package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order", schema = "ecommerce")
public class Order {
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(mappedBy = "order")
  private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

  @OneToOne
  private Payment payment;

  @Column(name = "status")
  private String status;

  @Column(name = "city")
  private String city;

  @Column(name = "street")
  private String street;

  @Column(name = "post_number")
  private String post_number;
}
