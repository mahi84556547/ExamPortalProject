package com.examportal.server.services;

import com.examportal.server.models.Category;

import java.util.Set;

public interface CategoryService {
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long categoryId);
}
