package com.backend.ecommerce.domain.entities;

import com.backend.ecommerce.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class User {
    private final int id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private String password;
    private final UserRole userRole;
}
