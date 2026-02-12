package com.gevernova.TaskManagement.controller;

import com.gevernova.TaskManagement.entity.Priority;
import com.gevernova.TaskManagement.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

     // Create a new Priority
    @PostMapping
    public ResponseEntity<Priority> createPriority(@RequestBody Priority priority) {
        return new ResponseEntity<>(priorityService.createPriority(priority), HttpStatus.CREATED);
    }

    // Get all Priorities
    @GetMapping
    public ResponseEntity<List<Priority>> getAllPriorities() {
        return ResponseEntity.ok(priorityService.getAllPriorities());
    }

    // Get a Priority by ID
    @GetMapping("/{priorityId}")
    public ResponseEntity<Priority> getPriorityById(@PathVariable Long priorityId) {
        return ResponseEntity.ok(priorityService.getPriorityById(priorityId));
    }

     // Update an existing Priority using ID
    @PutMapping("/{priorityId}")
    public ResponseEntity<Priority> updatePriority(@PathVariable Long priorityId, @RequestBody Priority priority) {
        return ResponseEntity.ok(priorityService.updatePriority(priorityId, priority));
    }

     // Delete a Priority by ID
    @DeleteMapping("/{priorityId}")
    public ResponseEntity<String> deletePriority(@PathVariable Long priorityId) {
        return ResponseEntity.ok(priorityService.deletePriority(priorityId));
    }
}
