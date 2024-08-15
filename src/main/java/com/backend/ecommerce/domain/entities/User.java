package com.backend.ecommerce.domain.entities;

import com.backend.ecommerce.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "ecommerce")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "type", columnDefinition = "enum('ADMIN', 'USER')")
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Order> orders;
}
