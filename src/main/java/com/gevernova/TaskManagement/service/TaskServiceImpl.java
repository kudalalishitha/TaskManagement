package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.dto.TaskRequest;
import com.gevernova.TaskManagement.dto.TaskStatusUpdateRequest;
import com.gevernova.TaskManagement.entity.*;
import com.gevernova.TaskManagement.exception.TaskNotFoundException;
import com.gevernova.TaskManagement.exception.UserNotFoundException;
import com.gevernova.TaskManagement.repository.CategoryRepository;
import com.gevernova.TaskManagement.repository.PriorityRepository;
import com.gevernova.TaskManagement.repository.TaskRepository;
import com.gevernova.TaskManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    @Override
    public Task createTask(TaskRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + request.getPriorityId()));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.PENDING)
                .user(user)
                .category(category)
                .priority(priority)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
    }

    @Override
    public Task updateTask(Long taskId, TaskRequest request) {

        Task existing = getTaskById(taskId);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + request.getPriorityId()));

        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setUser(user);
        existing.setCategory(category);
        existing.setPriority(priority);

        return taskRepository.save(existing);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatusUpdateRequest request) {

        Task existing = getTaskById(taskId);

        existing.setStatus(request.getStatus());

        return taskRepository.save(existing);
    }

    @Override
    public String deleteTask(Long taskId) {
        Task existing = getTaskById(taskId);
        taskRepository.delete(existing);
        return "Task deleted successfully with id: " + taskId;
    }
}
