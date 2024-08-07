package com.backend.ecommerce.application;

import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
//
//    public Optional<User> getUserById(int id) {
//        return userRepository.getUserById(id);
//    }
//
//    public boolean createUser(User user) {
//        return userRepository.createUser(user);
//    }
//
//    public boolean updateUser(User user) {
//        return userRepository.updateUser(user);
//    }
//
//    public boolean deleteUser(int id) {
//        return userRepository.deleteUser(id);
//    }
}
