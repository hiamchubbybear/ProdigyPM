package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.employer.model.Invalid;

public interface InvalidRepository extends JpaRepository<Invalid, String> {

}
