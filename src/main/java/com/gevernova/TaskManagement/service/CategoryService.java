package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category updateCategory(Long categoryId, Category category);

    String deleteCategory(Long categoryId);
}
