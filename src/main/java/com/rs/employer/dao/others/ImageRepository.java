package com.rs.employer.dao.others;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.others.Image;

@Repository

public interface ImageRepository extends JpaRepository<Image, UUID> {
    public List<Image> findAllByProducts_ProductId(Long productId);

    public List<Image> findAllByFileType(String filetype);

    public List<Image> findAllByFileName(String filename);
}
