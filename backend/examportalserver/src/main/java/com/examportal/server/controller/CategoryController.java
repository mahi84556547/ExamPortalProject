package com.examportal.server.controller;

import com.examportal.server.models.Category;
import com.examportal.server.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Set<Category>> getCategories(){
        Set<Category> categorySet=categoryService.getCategories();
        return new ResponseEntity<Set<Category>>(categorySet, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable(name = "categoryId") Long categoryId){
        Category category=categoryService.getCategory(categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category saveCategory=categoryService.createCategory(category);
        return new ResponseEntity<Category>(saveCategory, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updateCategory=categoryService.createCategory(category);
        return new ResponseEntity<Category>(updateCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> udeleteCategory(@PathVariable(name = "categoryId") Long categoryIdy){
        categoryService.deleteCategory(categoryIdy);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
