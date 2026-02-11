package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.dto.UserRequest;
import com.gevernova.TaskManagement.entity.User;
import com.gevernova.TaskManagement.exception.UserNotFoundException;
import com.gevernova.TaskManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User updateUser(Long userId, UserRequest request) {
        User existing = getUserById(userId);

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());

        return userRepository.save(existing);
    }

    @Override
    public String deleteUser(Long userId) {
        User existing = getUserById(userId);
        userRepository.delete(existing);
        return "User deleted successfully with id: " + userId;
    }
}
