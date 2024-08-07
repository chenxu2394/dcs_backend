package com.backend.ecommerce.domain.entities;

import com.backend.ecommerce.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "ecommerce")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String email;
    private String password;
//    @Enumerated(EnumType.ORDINAL)  // Maps enum to a smallint
//    @Column(name = "type")  // Maps to the 'type' column in the SQL table
//    private UserRole userRole;
    private int type;
}
