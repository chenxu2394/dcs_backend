package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(UUID id);
}
