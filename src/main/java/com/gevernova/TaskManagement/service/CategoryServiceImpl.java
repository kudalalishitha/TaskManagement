package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Category;
import com.gevernova.TaskManagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Service layer component
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository; // Repository for DB operations

    @Override
    public Category createCategory(Category category) {
        // Save new category into DB
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        // Fetch all categories from DB
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        // Fetch category by ID (if not found -> throw exception)
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        // Fetch existing category
        Category existing = getCategoryById(categoryId);

        // Update category fields
        existing.setName(category.getName());

        // Save updated category
        return categoryRepository.save(existing);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        // Fetch existing category
        Category existing = getCategoryById(categoryId);

        // Delete from DB
        categoryRepository.delete(existing);

        // Return success message
        return "Category deleted successfully with id: " + categoryId;
    }
}

