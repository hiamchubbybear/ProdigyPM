package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Category;


public interface  ICategoryService {
    public Category addCategory(Category request);
    public Category updateCategory(Category request);
    public Category getCategoryByName(String name);
    public void deleteCategory(Long id);
    public Category getCategoryById(Long id);
    public List<Category> allCategory();

}
