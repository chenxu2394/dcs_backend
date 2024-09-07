package com.backend.ecommerce.application;

import com.backend.ecommerce.application.dto.user.ReturnedDto;
import com.backend.ecommerce.application.dto.user.UpdateUserDto;
import com.backend.ecommerce.application.mapper.UserMapper;
import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.interfaces.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<ReturnedDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return users.stream().map(userMapper::toReturnedDto).toList();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = userRepository.getUserByEmail(username);

        return result.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
    }

    public ReturnedDto updateUser(UpdateUserDto user) {
        var updateUserDomain = userMapper.toUserFromUpdate(user);
        var oldUser = userRepository.getUserById(user.id());

        if (oldUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Set the password from the old user to the updated user
        updateUserDomain.setPassword(oldUser.get().getPassword());

        var res = userRepository.updateUser(updateUserDomain);
        return userMapper.toReturnedDto(res);
    }
}
