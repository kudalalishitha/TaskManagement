package com.gevernova.TaskManagement.dto;

import com.gevernova.TaskManagement.entity.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskStatusUpdateRequest {
    private TaskStatus status;
}
