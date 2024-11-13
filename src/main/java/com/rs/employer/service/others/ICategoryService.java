package com.rs.employer.service.others;

import com.rs.employer.model.others.Category;

import java.util.List;



public interface  ICategoryService {
    public Category addCategory(Category request);
    public Category updateCategory(Category request);
    public Category getCategoryByName(String name);
    public void deleteCategory(Long id);
    public Category getCategoryById(Long id);
    public List<Category> allCategory();

}
