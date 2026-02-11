package com.gevernova.TaskManagement.service;

import com.gevernova.TaskManagement.entity.Category;
import com.gevernova.TaskManagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existing = getCategoryById(categoryId);
        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category existing = getCategoryById(categoryId);
        categoryRepository.delete(existing);
        return "Category deleted successfully with id: " + categoryId;
    }
}
