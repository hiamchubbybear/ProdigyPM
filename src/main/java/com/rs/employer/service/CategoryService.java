package com.rs.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CategoryRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Category;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Category addCategory(Category request) {
        if (!categoryRepository.existsById(request.getId())) {
            return categoryRepository.save(request);
        } else {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
    }
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public Category updateCategory(Category role) {
        return null;
    }
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse( categoryRepository::delete , () -> {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION); });
    }


    @Override
    public List<Category> allCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOTFOUND));
    }

}
