package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(UUID id);
}
