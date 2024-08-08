package com.backend.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment", schema = "ecommerce")
public class Payment {
  @Id
  @Column(name="id")
  private int id;

  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;

  @Column(name="amount")
  private float amount;

  private String city;
  private String street;
  private String postNumber;
  private boolean paymentStatus;

}
