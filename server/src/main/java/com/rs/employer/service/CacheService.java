package com.rs.employer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void evictCaches(String key) {
        Cache customerInfoCache = cacheManager.getCache("customerInfo");
        if (customerInfoCache != null) {
            customerInfoCache.evict(key);
        }

        Cache customerCache = cacheManager.getCache("customer");
        if (customerCache != null) {
            customerCache.evict(key);
            customerCache.evict("allCustomers"); // Xóa cache `allCustomers` nếu tồn tại
        }

        Cache userAddedCache = cacheManager.getCache("userAdded");
        if (userAddedCache != null) {
            userAddedCache.evict(key);
        }
    }
}
