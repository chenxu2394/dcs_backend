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
  @Column(name="id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(mappedBy = "order")
  private List<OrderProduct> orderProduct = new ArrayList<OrderProduct>();

  @OneToOne
  @JoinColumn(name = "payment_id", referencedColumnName = "id")
  private Payment payment;

  @Column(name = "status")
  private String status;

  @Column(name = "city")
  private String city;

  @Column(name = "street")
  private String street;

  @Column(name = "postnumber")
  private String post_number;
}
