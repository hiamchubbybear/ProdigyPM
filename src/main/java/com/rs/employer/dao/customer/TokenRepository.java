package com.rs.employer.dao.customer;

import com.rs.employer.model.customer.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
        Optional<Token> findByToken(String token);
        void deleteByExpireDateBefore(LocalDateTime now);
        Optional<Token> findTokenByCustomerEmailAndUsed(String username , boolean used);
        boolean existsByToken(String newToken);
}