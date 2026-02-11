package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.dto.UserRequest;
import com.gevernova.TaskManagement.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserRequest request);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, UserRequest request);

    String deleteUser(Long userId);
}
