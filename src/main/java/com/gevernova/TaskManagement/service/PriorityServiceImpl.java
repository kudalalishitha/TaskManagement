package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Priority;
import com.gevernova.TaskManagement.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Override
    public Priority createPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public Priority getPriorityById(Long priorityId) {
        return priorityRepository.findById(priorityId)
                .orElseThrow(() -> new RuntimeException("Priority not found with id: " + priorityId));
    }

    @Override
    public Priority updatePriority(Long priorityId, Priority priority) {
        Priority existing = getPriorityById(priorityId);
        existing.setName(priority.getName());
        return priorityRepository.save(existing);
    }

    @Override
    public String deletePriority(Long priorityId) {
        Priority existing = getPriorityById(priorityId);
        priorityRepository.delete(existing);
        return "Priority deleted successfully with id: " + priorityId;
    }
}
