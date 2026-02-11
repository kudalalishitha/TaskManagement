package com.gevernova.TaskManagement.repository;

import com.gevernova.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
