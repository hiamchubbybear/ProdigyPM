package com.rs.employer.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.others.Image;

@Repository

public interface ImageRepository extends JpaRepository<Image, UUID> {
    public List<Image> findAllByProductsId(Long productId);

    public List<Image> findByfileType(String filetype);

    public List<Image> findByfileName(String filename);
}
