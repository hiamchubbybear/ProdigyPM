package com.rs.employer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Image;
@Repository
public interface  ImageRepository extends JpaRepository<Image, Long> {

}
