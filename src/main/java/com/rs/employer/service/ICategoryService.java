package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Category;


public interface  ICategoryService {
    public Category addCategory(Category request);

    public Category updateCategory(Category request);

    public Boolean deleteCategory(Long id);

    public List<Category> allCategory();

}
