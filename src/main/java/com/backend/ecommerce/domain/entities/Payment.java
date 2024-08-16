package com.backend.ecommerce.domain.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "payment", schema = "ecommerce")
public class Payment {
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne
  private Order order;

  @Column(name="amount", columnDefinition = "numeric(24,2)")
  private float amount;

  private String city;
  private String street;

  @Column(name = "post_number", columnDefinition="bpchar(5)")
  private String postNumber;

  @Column(name = "paymentStatus")
  private boolean paymentStatus;
}
