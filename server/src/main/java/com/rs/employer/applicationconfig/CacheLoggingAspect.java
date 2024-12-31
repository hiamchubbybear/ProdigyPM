package com.rs.employer.applicationconfig;

import com.rs.employer.model.customer.Customer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheLoggingAspect.class);

    @After("@annotation(org.springframework.cache.annotation.Cacheable) && args(username)")
    public void logAfterCacheable(JoinPoint joinPoint, String username) {
        log.info("Cacheable method called with username: {}", username);
    }

    @After("@annotation(org.springframework.cache.annotation.CachePut) && args(customer)")
    public void logAfterCachePut(JoinPoint joinPoint, Customer customer) {
        log.info("CachePut method called for customer: {}", customer.getUsername());
    }

    @After("@annotation(org.springframework.cache.annotation.CacheEvict) && args(username)")
    public void logAfterCacheEvict(JoinPoint joinPoint, String username) {
        log.info("CacheEvict method called for username: {}", username);
    }
}