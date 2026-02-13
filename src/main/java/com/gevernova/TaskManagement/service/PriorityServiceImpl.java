package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Priority;
import com.gevernova.TaskManagement.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Service layer component
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository; // Repository for Priority DB operations

    @Override
    public Priority createPriority(Priority priority) {
        // Save new priority into DB
        return priorityRepository.save(priority);
    }

    @Override
    public List<Priority> getAllPriorities() {
        // Fetch all priorities from DB
        return priorityRepository.findAll();
    }

    @Override
    public Priority getPriorityById(Long priorityId) {
        // Fetch priority by ID (if not found -> throw exception)
        return priorityRepository.findById(priorityId)
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + priorityId));
    }

    @Override
    public Priority updatePriority(Long priorityId, Priority priority) {
        // Fetch existing priority
        Priority existing = getPriorityById(priorityId);

        // Update priority fields
        existing.setName(priority.getName());

        // Save updated priority
        return priorityRepository.save(existing);
    }

    @Override
    public String deletePriority(Long priorityId) {
        // Fetch existing priority
        Priority existing = getPriorityById(priorityId);

        // Delete from DB
        priorityRepository.delete(existing);

        // Return success message
        return "Priority deleted successfully with id: " + priorityId;
    }
}
