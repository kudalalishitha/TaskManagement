package com.gevernova.TaskManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    private String title;
    private String description;

    private Long userId;
    private Long categoryId;
    private Long priorityId;
}
