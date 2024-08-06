package com.backend.ecommerce.infastructure;

import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.enums.UserRole;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Database {
    private final List<User> users;

    public Database() {
        this.users = new ArrayList<>(List.of(
                new User(1, "admin", "admin@example.com", "adminPassword", UserRole.ADMIN),
                new User(2, "user", "user@example.com", "userPassword", UserRole.USER)
        ));
    }
}
