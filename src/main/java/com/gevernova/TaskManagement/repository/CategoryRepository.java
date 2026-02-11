package com.gevernova.TaskManagement.repository;

import com.gevernova.TaskManagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
