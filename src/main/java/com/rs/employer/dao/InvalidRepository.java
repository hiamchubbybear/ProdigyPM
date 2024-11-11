package com.rs.employer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.employer.model.others.Invalid;

public interface InvalidRepository extends JpaRepository<Invalid, String> {

}
