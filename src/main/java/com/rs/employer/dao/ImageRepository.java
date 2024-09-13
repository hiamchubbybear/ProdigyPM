package com.rs.employer.dao;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.rs.employer.dto.Request.ImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Image;

@Repository

public interface ImageRepository extends JpaRepository<Image, UUID> {
    public List<Image> findAllByProductsId(Long productId);

    public List<Image> findByfileType(String filetype);

    public List<Image> findByfileName(String filename);
//    public List<Image> findAllByProductsId(Long productId);w
}
