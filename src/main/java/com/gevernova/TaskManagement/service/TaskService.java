package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.dto.TaskRequest;
import com.gevernova.TaskManagement.dto.TaskStatusUpdateRequest;
import com.gevernova.TaskManagement.entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(TaskRequest request);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    Task updateTask(Long taskId, TaskRequest request);

    Task updateTaskStatus(Long taskId, TaskStatusUpdateRequest request);

    String deleteTask(Long taskId);
}
