package com.rs.employer.dao.purchase;

import com.rs.employer.model.sale.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}
