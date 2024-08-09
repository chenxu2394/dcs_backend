package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.interfaces.IUserRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return jpaUserRepository.findAll().stream().toList();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return jpaUserRepository.findById(id);
    }
//
//    @Override
//    public boolean createUser(User user) {
//        try {
//            this.users.add(user);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean updateUser(User user) {
//        try {
//            this.users.set(this.users.indexOf(user), user);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean deleteUser(int id) {
//        try {
//            this.users.removeIf(user -> user.getId() == id);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
