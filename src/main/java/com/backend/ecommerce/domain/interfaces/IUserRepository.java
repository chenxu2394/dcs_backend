package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    public List<User> getAllUsers();
    public Optional<User> getUserById(UUID id);
//    public boolean createUser(User user);
//    public boolean updateUser(User user);
//    public boolean deleteUser(int id);
}
