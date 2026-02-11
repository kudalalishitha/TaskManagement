package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Priority;

import java.util.List;

public interface PriorityService {

    Priority createPriority(Priority priority);

    List<Priority> getAllPriorities();

    Priority getPriorityById(Long priorityId);

    Priority updatePriority(Long priorityId, Priority priority);

    String deletePriority(Long priorityId);
}
