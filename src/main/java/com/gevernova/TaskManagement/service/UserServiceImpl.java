package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.dto.UserRequest;
import com.gevernova.TaskManagement.entity.User;
import com.gevernova.TaskManagement.exception.UserNotFoundException;
import com.gevernova.TaskManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Service layer component
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Repository for User DB operations

    @Override
    public User createUser(UserRequest request) {

        // Build User entity using request data
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        // Save user into DB and return saved object
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Fetch all users from DB
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        // Fetch user by ID (if not found -> throw UserNotFoundException)
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User updateUser(Long userId, UserRequest request) {

        // Fetch existing user by ID
        User existing = getUserById(userId);

        // Update user fields
        existing.setName(request.getName());
        existing.setEmail(request.getEmail());

        // Save updated user
        return userRepository.save(existing);
    }

    @Override
    public String deleteUser(Long userId) {
        // Fetch existing user by ID
        User existing = getUserById(userId);

        // Delete user from DB
        userRepository.delete(existing);

        // Return success message
        return "User deleted successfully with id: " + userId;
    }
}

