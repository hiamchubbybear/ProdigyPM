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
    CategoryRepository repo;
    @Override
    // @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Category addCategory(Category request) {
        if (repo.existsById(request.getId())) {
            return repo.save(request);
        } else {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
    }
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public Category updateCategory(Category role) {
        // Optional<Customer> customer1 = customerRepository.findById(id);
        // if (customer1.isPresent()) {

        //     var roles = roleRepository.findAllById(customer.getRole());
        //     Customer customer3 = mapper.toCustomer(customer);
        //     customer3.setUpdate(now);
        //     customer3.setRoles(new HashSet<>(roles));
        //     customer3.setPassword(passwordEncoder.encode(customer.getPassword()));
        //     return customerRepository.save(customer3);
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public Boolean deleteCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

    @Override
    public List<Category> allCategory() {
        return repo.findAll();
    }

}
