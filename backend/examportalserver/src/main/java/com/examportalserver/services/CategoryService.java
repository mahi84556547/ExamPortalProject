package com.examportalserver.services;

import com.examportalserver.models.Category;

import java.util.Set;

public interface CategoryService {
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long categoryId);
}
