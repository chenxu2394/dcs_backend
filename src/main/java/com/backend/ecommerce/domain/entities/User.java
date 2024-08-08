package com.backend.ecommerce.domain.entities;

import com.backend.ecommerce.domain.enums.UserRole;
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
//    @Enumerated(EnumType.ORDINAL)  // Maps enum to a smallint
//    @Column(name = "type")  // Maps to the 'type' column in the SQL table
//    private UserRole userRole;
    @Column(name="type")
    private int type;

    @OneToMany(mappedBy = "user")
    List<Order> orders;
}
