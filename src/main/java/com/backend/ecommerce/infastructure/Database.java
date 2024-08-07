package com.backend.ecommerce.infastructure;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.enums.UserRole;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Database {
    private final List<User> users;
    private final List<Order> orders;

    User user1 = new User(1, "admin", "admin@example.com", "adminPassword", UserRole.ADMIN);
    User user2 = new User(2, "user", "user@example.com", "userPassword", UserRole.USER);
    Product product1 = new Product();
    Product product2 = new Product();
    Order order1 = new Order(UUID.fromString("e0f78877-49cb-49ab-9edf-411b3dff3b52)"), user2, new ArrayList<Product>(List.of(product1,product2)), "City", "Street", "01234");

    public Database() {
        this.users = new ArrayList<>(List.of(
                user1, user2
        ));

        this.orders = new ArrayList<>(List.of(
                order1
        ));
    }
}
