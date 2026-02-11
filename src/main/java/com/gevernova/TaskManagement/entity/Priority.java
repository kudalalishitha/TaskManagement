package com.gevernova.TaskManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "priorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Example: LOW, MEDIUM, HIGH
}
