package com.backend.ecommerce.presentation;


import com.backend.ecommerce.abstraction.UserService;
import com.backend.ecommerce.application.AuthServiceImpl;
import com.backend.ecommerce.application.dto.user.RegisterDto;
import com.backend.ecommerce.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthServiceImpl authService;

    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id){
        var user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }

}
