package com.rs.employer.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.AuthenticationDto;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.repository.CustomerRepo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImp {
    @Autowired
    private CustomerRepo repo;

    public boolean authenticate(AuthenticationDto authentication) {
        var user = repo.findByUsername(authentication.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
        return passwordEncoder.matches(authentication.getPassword(), user.getPassword());

    }
}
