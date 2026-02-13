package com.gevernova.TaskManagement.controller;

import com.gevernova.TaskManagement.dto.UserRequest;
import com.gevernova.TaskManagement.entity.User;
import com.gevernova.TaskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST Controller
@RequestMapping("/api/users") // Base URL for all User APIs
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class UserController {

    private final UserService userService; // Service layer object

    // Create a new User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    // Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get a User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // Update an existing User by ID
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    // Delete a User by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}

