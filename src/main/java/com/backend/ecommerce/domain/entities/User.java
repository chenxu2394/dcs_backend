package com.backend.ecommerce.domain.entities;

import com.backend.ecommerce.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user", schema = "ecommerce")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @Column(name = "type", columnDefinition = "enum('ADMIN', 'USER')")
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;
}
