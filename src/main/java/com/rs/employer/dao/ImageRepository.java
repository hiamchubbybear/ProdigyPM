package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    public List<Image> findAllByProductsId(Long productId);

    public List<Image> findByfileType(String filetype);

    public List<Image> findByTypeName(String filename);

}
