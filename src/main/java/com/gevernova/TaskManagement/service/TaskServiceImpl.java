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

@Service // Marks this class as a Service layer component
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository; // Repository for Task DB operations
    private final UserRepository userRepository; // Repository for User DB operations
    private final CategoryRepository categoryRepository; // Repository for Category DB operations
    private final PriorityRepository priorityRepository; // Repository for Priority DB operations

    @Override
    public Task createTask(TaskRequest request) {

        // Fetch user using userId (if not found -> throw exception)
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        // Fetch category using categoryId
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        // Fetch priority using priorityId
        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + request.getPriorityId()));

        // Build Task object using request data
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.PENDING) // Default status when task is created
                .user(user)
                .category(category)
                .priority(priority)
                .build();

        // Save task in DB and return saved object
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        // Fetch all tasks from DB
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        // Fetch task by ID (if not found -> throw TaskNotFoundException)
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
    }

    @Override
    public Task updateTask(Long taskId, TaskRequest request) {

        // Fetch existing task by ID
        Task existing = getTaskById(taskId);

        // Fetch user using userId
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        // Fetch category using categoryId
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        // Fetch priority using priorityId
        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + request.getPriorityId()));

        // Update task fields
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setUser(user);
        existing.setCategory(category);
        existing.setPriority(priority);

        // Save updated task
        return taskRepository.save(existing);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatusUpdateRequest request) {

        // Fetch existing task by ID
        Task existing = getTaskById(taskId);

        // Update only status
        existing.setStatus(request.getStatus());

        // Save updated task
        return taskRepository.save(existing);
    }

    @Override
    public String deleteTask(Long taskId) {
        // Fetch existing task by ID
        Task existing = getTaskById(taskId);

        // Delete task from DB
        taskRepository.delete(existing);

        // Return success message
        return "Task deleted successfully with id: " + taskId;
    }
}
