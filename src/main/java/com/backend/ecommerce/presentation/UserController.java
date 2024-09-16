package com.backend.ecommerce.presentation;

import com.backend.ecommerce.application.AuthServiceImpl;
import com.backend.ecommerce.abstraction.UserService;
import com.backend.ecommerce.application.dto.user.LoginDto;
import com.backend.ecommerce.application.dto.user.RegisterDto;
import com.backend.ecommerce.application.dto.user.ReturnedDto;
import com.backend.ecommerce.application.dto.user.UpdateUserDto;
import com.backend.ecommerce.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthServiceImpl authService;
    private final UserService userServiceImpl;

    public UserController(AuthServiceImpl authService, UserService userServiceImpl) {
        this.authService = authService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<ReturnedDto>> getAllUsers() {
        var users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id.toString() == authentication.principal.getId().toString()")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        var user = userServiceImpl.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return authService.authenticate(loginDto);
    }

    @PutMapping()
    public ResponseEntity<ReturnedDto> updateUser(@RequestBody UpdateUserDto user) {
        var updatedUser = userServiceImpl.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
